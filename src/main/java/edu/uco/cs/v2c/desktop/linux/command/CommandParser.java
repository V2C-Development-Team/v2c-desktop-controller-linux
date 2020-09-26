package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.desktop.linux.model.CommandDataTable;
import edu.uco.cs.v2c.dispatcher.api.listener.CommandListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteCommandPayload;

/**
 * Parses incoming commands.
 * 
 * @author Caleb L. Power
 */
public class CommandParser implements CommandListener {
  
  private CommandDataTable commandDataTable = null;
  
  /**
   * Instantiates the command parser.
   * 
   * @param commandDataTable the command data table
   */
  public CommandParser(CommandDataTable commandDataTable) {
    this.commandDataTable = commandDataTable;
  }

  /**
   * {@inheritDoc}
   */
  @Override public void onIncomingCommand(RouteCommandPayload payload) {
    System.out.printf("COMMAND for %1$s = %2$s", payload.getRecipient(), payload.getCommand());
    String targetCommand = payload.getCommand().toString();
    for(int i = 0; i < commandDataTable.getRowCount(); i++) {
      String[] row = (String[])commandDataTable.getRowAt(i);
      if(row[2].equalsIgnoreCase(targetCommand)) {
        targetCommand = row[3];
        break;
      }
    }
    TerminalCommandJava tempCommand = new TerminalCommandJava();
		// tempCommand.ExecuteCommand("ping google.com");
		tempCommand.ExecuteCommand(targetCommand);
    
  }

}
