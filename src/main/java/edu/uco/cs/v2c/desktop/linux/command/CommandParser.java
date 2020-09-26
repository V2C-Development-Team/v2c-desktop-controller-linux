package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.dispatcher.api.listener.CommandListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteCommandPayload;

/**
 * Parses incoming commands.
 * 
 * @author Caleb L. Power
 */
public class CommandParser implements CommandListener {

  /**
   * {@inheritDoc}
   */
  @Override public void onIncomingCommand(RouteCommandPayload payload) {
    System.out.printf("COMMAND for %1$s = %2$s", payload.getRecipient(), payload.getCommand());
    String targetCommand = payload.getCommand().toString();
    TerminalCommandJava tempCommand = new TerminalCommandJava();
		// tempCommand.ExecuteCommand("ping google.com");
		tempCommand.ExecuteCommand(targetCommand);
    
  }

}
