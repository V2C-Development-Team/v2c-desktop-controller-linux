package edu.uco.cs.v2c.desktop.linux.command;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

import edu.uco.cs.v2c.desktop.linux.model.CommandState;
import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.desktop.linux.model.RecognitionState;
import edu.uco.cs.v2c.desktop.linux.model.RecognitionStateContext;
import edu.uco.cs.v2c.desktop.linux.model.TypingState;

public class StateMachine implements Runnable {
	private static final String MODE_SWITCH_KEYWORD = "alteration";
	private static final String TYPING_MODE = "typing";
	private static final String COMMAND_MODE = "command";
	private static final String TAB = "tab";
	private static final String WINDOW_NEXT = "window";
	private static final String LOG_LABEL = "DESKTOP_STATEMACHINE";
	private ConfigurationData configurationData = null;
	
	private LinkedList<String> incomingBuffer = new LinkedList<>();//buffer for incoming commands
	private LinkedList<String> confirmedBuffer = new LinkedList<>();// buffer for building targeted outgoing commands to modules
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
		if(confirmedBuffer.isEmpty()) return; //if buffer is empty it is flushed
		
		StringBuilder stringBuilder = new StringBuilder();//a builder for building the outgoing command
		do
			stringBuilder.append(confirmedBuffer.remove(0)).append(' ');
		while(!confirmedBuffer.isEmpty());// while buffer !empty append token at 0, and a space
		// this is essentially reconstructing our tokenized string
		if(stringBuilder.toString()!= null)
		myState.execute(stringBuilder.toString().stripTrailing(), configurationData);
	}
	
	public void queue(String input) {
		String tokens [] = input.split("\\s+"); // take the input string and tokenize it, 1-* spaces are token delimiters.
		synchronized(incomingBuffer) {
			for(String token: tokens)
				incomingBuffer.add(token.toLowerCase());// synchonized for multithreading, add each token to buffer
			incomingBuffer.notifyAll(); //notify all waiting threads of update.
		}
	}
	
	public void kill() {
		thread.interrupt();
	}


	@Override
	public void run() {
		try {
			boolean foundModeSwitch = false;
			
			while(!thread.isInterrupted()) {
				String token = null;
				
				synchronized(incomingBuffer){
					while(incomingBuffer.isEmpty()) {
						flush();
						incomingBuffer.wait();
					}
					token = incomingBuffer.remove(0);
					incomingBuffer.notifyAll();
				}
				if(foundModeSwitch) {
					if(token.equalsIgnoreCase(TYPING_MODE)) {
						flush();
						myState.setState(new TypingState());
					}
					else if(token.equalsIgnoreCase(COMMAND_MODE)) {
						flush();
						myState.setState(new CommandState());
					}
					else if(token.equalsIgnoreCase(TAB)) {
							KeyboardRobot.switchTextbox();
						}
					else if(token.equalsIgnoreCase(WINDOW_NEXT)) {
						 KeyboardRobot.windowNext();
					}
						
					
					else {
						confirmedBuffer.add(MODE_SWITCH_KEYWORD);
						confirmedBuffer.add(token);
					}
					
					foundModeSwitch = false;
					
				}else if(token.equalsIgnoreCase(MODE_SWITCH_KEYWORD)) {
					foundModeSwitch = true;
				}else confirmedBuffer.add(token);
				
			}
		}
		catch(InterruptedException e) {}
		
	}
	
	
}
