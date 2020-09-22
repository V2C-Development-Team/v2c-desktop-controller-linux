/*
 * Copyright (c) 2020 V2C Development Team. All rights reserved.
 * Licensed under the Version 0.0.1 of the V2C License (the "License").
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at <https://tinyurl.com/v2c-license>.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions 
 * limitations under the License.
 */
package edu.uco.cs.v2c.desktop.linux;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import edu.uco.cs.v2c.desktop.linux.command.CommandParser;
import edu.uco.cs.v2c.desktop.linux.log.Logger;
import edu.uco.cs.v2c.desktop.linux.net.DispatcherHandler;
import edu.uco.cs.v2c.desktop.linux.ui.LocalUI;

/**
 * Natively delegates tasks for generic desktop applications to execute in
 * accordance with messages received from the V2C Dispatcher.
 * 
 * @author Caleb L. Power
 */
public class V2CLinuxDesktopController {
  
  private static final String DEFAULT_DESTINATION = "ws://127.0.0.1:2585/v1/messages";
  private static final String DESTINATION_PARAM_LONG = "destination";
  private static final String DESTINATION_PARAM_SHORT = "d";
  private static final String LOG_LABEL = "CONTROLLER";
  
  /**
   * Entry-point.
   * 
   * @param args the program arguments
   */
  public static void main(String[] args) {
    try {      
      Options options = new Options();
      options.addOption(DESTINATION_PARAM_SHORT, DESTINATION_PARAM_LONG, true,
          "Specifies the default dispatcher address. Default = " + DEFAULT_DESTINATION);
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);
      
      String destination = null;
      
      if(cmd.hasOption(DESTINATION_PARAM_LONG)) {
        Logger.onDebug(LOG_LABEL, "Picked up an explicitly specified destination.");
        destination = cmd.getOptionValue(DESTINATION_PARAM_LONG);
      } else {
        Logger.onDebug(LOG_LABEL, "No port specified, falling back to default.");
        destination = DEFAULT_DESTINATION;
      }
      
      Logger.onInfo(LOG_LABEL, String.format("Intending to connect to dispatcher at %1$s.", destination));
      
      CommandParser commandParser = new CommandParser();
      DispatcherHandler handler = DispatcherHandler.build(destination);
      handler.registerCommandListener(commandParser);
      
      // catch CTRL + C
      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override public void run() {
          handler.kill();
          try {
            Thread.sleep(1000L);
          } catch(InterruptedException e) { }
        }
      });
      
      LocalUI localUI = new LocalUI();
      localUI.init();
      
    } catch(Exception e) {
      Logger.onError(LOG_LABEL, "Exception thrown: "
          + (e.getMessage() == null ? "Unknown." : e.getMessage()));
      e.printStackTrace();
    }
  }
  
}
