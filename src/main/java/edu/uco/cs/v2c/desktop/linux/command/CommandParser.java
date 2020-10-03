package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.dispatcher.api.listener.CommandListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteCommandPayload;

/**
 * Parses incoming commands.
 * 
 * @author Caleb L. Power
 */
public class CommandParser implements CommandListener {

  private ConfigurationData configurationData;

  /**
   * Instantiates the command parser.
   * 
   * @param commandDataTable the command data table
   */
  public CommandParser(ConfigurationData configurationData){
    this.configurationData = configurationData;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onIncomingCommand(RouteCommandPayload payload) {
    String targetCommand = payload.getCommand();
    System.out.println(targetCommand);

    // check recipient 
    
    // find targetCommand in configurationdata

    TerminalCommandJava tempCommand = new TerminalCommandJava();
    tempCommand.ExecuteCommand(targetCommand);

  }
}
