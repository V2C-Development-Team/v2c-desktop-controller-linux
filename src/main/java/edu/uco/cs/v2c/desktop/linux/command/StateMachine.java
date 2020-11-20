package edu.uco.cs.v2c.desktop.linux.command;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

import edu.uco.cs.v2c.desktop.linux.model.CommandState;
import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.desktop.linux.model.RecognitionState;
import edu.uco.cs.v2c.desktop.linux.model.RecognitionStateContext;
import edu.uco.cs.v2c.desktop.linux.model.StreamState;
import edu.uco.cs.v2c.desktop.linux.model.TypingState;

public class StateMachine implements Runnable {
	private static final String MODE_SWITCH_KEYWORD = "alteration";
	private static final String TYPING_MODE = "typing";
	private static final String COMMAND_MODE = "command";
	private static final String STREAMING_MODE = "streaming";
	private static final String TAB = "tab";
	private static final String ALT = "alt";
	private static final String SELECT = "select";
	private static final String COPY = "copy";
	private static final String PASTE = "paste";
	private static final String MAXIMIZE = "maximize";
	private static final String MINIMIZE = "minimize";
	private static final String UNDO = "undo";
	private static final String LOG_LABEL = "DESKTOP_STATEMACHINE";
	private ConfigurationData configurationData = null;
	private boolean altHeld = false;
	private boolean shiftHeld = false;
	private LinkedList<String> incomingBuffer = new LinkedList<>();// buffer for incoming commands
	private LinkedList<String> confirmedBuffer = new LinkedList<>();// buffer for building targeted outgoing commands to
																	// modules
	private Thread thread = null;
	private RecognitionStateContext myState;

	private StateMachine(ConfigurationData config) {
		this.configurationData = config;

	}

	public static StateMachine build(ConfigurationData config, RecognitionStateContext context) {
		StateMachine machine = new StateMachine(config);
		machine.thread = new Thread(machine);
		machine.thread.setDaemon(true);
		machine.thread.start();
		machine.myState = context;
		return machine;
	}

	private void flush() {
		if (confirmedBuffer.isEmpty())
			return; // if buffer is empty it is flushed

		StringBuilder stringBuilder = new StringBuilder();// a builder for building the outgoing command
		do
			stringBuilder.append(confirmedBuffer.remove(0)).append(' ');
		while (!confirmedBuffer.isEmpty());// while buffer !empty append token at 0, and a space
		// this is essentially reconstructing our tokenized string
		if (stringBuilder.toString() != null)
			myState.execute(stringBuilder.toString().stripTrailing(), configurationData);
	}

	public void queue(String input) {
		String tokens[] = input.split("\\s+"); // take the input string and tokenize it, 1-* spaces are token
												// delimiters.
		synchronized (incomingBuffer) {
			for (String token : tokens)
				incomingBuffer.add(token.toLowerCase());// synchonized for multithreading, add each token to buffer
			incomingBuffer.notifyAll(); // notify all waiting threads of update.
		}
	}

	public void kill() {
		thread.interrupt();
	}

	@Override
	public void run() {
		try {
			boolean foundModeSwitch = false;

			while (!thread.isInterrupted()) {
				String token = null;
				
				synchronized (incomingBuffer) {
					while (incomingBuffer.isEmpty()) {
						flush();
						incomingBuffer.wait();
					}
					token = incomingBuffer.remove(0);					
					
					
					incomingBuffer.notifyAll();
				}
				if (foundModeSwitch) {
					switch(token.toLowerCase()) {
					case TYPING_MODE:
						flush();
						myState.setState(new TypingState());
						break;
					case COMMAND_MODE:
						flush();
						myState.setState(new CommandState());
						break;
					case STREAMING_MODE:
						flush();
						myState.setState(new StreamState());
						break;
					case ALT:
						if(altHeld) {
							KeyboardRobot.releaseAlt(); altHeld = false;
							}
						else {
							KeyboardRobot.holdAlt(); altHeld = true;
						}
						break;
					case SELECT:
						if(shiftHeld) {
							KeyboardRobot.releaseShift(); shiftHeld = false;
						}
						else {
							KeyboardRobot.holdShift(); shiftHeld = true;
						}
						break;
					case COPY:
						KeyboardRobot.copy();
						break;
					case PASTE:
						KeyboardRobot.paste();
						break;
					case TAB:
						KeyboardRobot.tab();
						break;
					case MAXIMIZE:
						KeyboardRobot.maximize();
						break;
					case MINIMIZE:
						KeyboardRobot.minimize();
						break;
					
					default:
						confirmedBuffer.add(MODE_SWITCH_KEYWORD);
						confirmedBuffer.add(token);
					
					}

					foundModeSwitch = false;

				} else if (token.equalsIgnoreCase(MODE_SWITCH_KEYWORD)) {
					foundModeSwitch = true;
						
				}else confirmedBuffer.add(token);

			}
		} catch (InterruptedException e) {
		}

	}

}

/*
 * This file is part of v2c-desktop-controller-linux.
 * 
 * v2c-desktop-controller-linux is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * v2c-desktop-controller-linux is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with v2c-desktop-controller-linux.  If not, see <https://www.gnu.org/licenses/>.
 */