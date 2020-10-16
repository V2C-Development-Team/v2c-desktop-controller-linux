package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.desktop.linux.model.Command;
import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.desktop.linux.model.Macro;
import edu.uco.cs.v2c.dispatcher.api.listener.CommandListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteCommandPayload;

/**
 * Parses incoming commands.
 * 
 * @author Caleb L. Power
 */
public class CommandParser implements CommandListener {
  private ConfigurationData configurationData;
  private static final String DESKTOP_VAR = "desktop";

  /**
   * Instantiates the command parser.
   * 
   * @param configurationData the configurationData model
   */
  public CommandParser(ConfigurationData configurationData) {
    this.configurationData = configurationData;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onIncomingCommand(RouteCommandPayload payload) {
    // TerminalCommandJava terminal = new TerminalCommandJava();

    String targetCommand = payload.getCommand();


    if (payload.getRecipient().equals(DESKTOP_VAR)) {
      String[] tokenCommands = targetCommand.split(" ");
      for (String s: tokenCommands) {
        Command foundCommand = configurationData.findCommand(s);
        Macro foundMacro = configurationData.findMacro(s);
        if (foundCommand != null && foundCommand.getEnabled()) {
          System.out.println("command found");
          foundCommand.execute();
        } else if (foundMacro != null && foundMacro.getEnabled()) {
          System.out.println("macro found");
          foundMacro.execute();
        } else {
          System.out.println("command/macro not found");
          // System.out.println("trying to execute anyway");
          // terminal.ExecuteCommand(targetCommand);
        }
      }      
    } else {
      System.out.println("=========recipient is not desktop========");
    }

    

   
    

    

  }
}
