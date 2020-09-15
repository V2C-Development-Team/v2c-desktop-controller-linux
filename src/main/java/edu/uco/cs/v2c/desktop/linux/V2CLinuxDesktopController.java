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

import edu.uco.cs.v2c.desktop.linux.log.Logger;

/**
 * Natively delegates tasks for generic desktop applications to execute in
 * accordance with messages received from the V2C Dispatcher.
 * 
 * @author Caleb L. Power
 */
public class V2CLinuxDesktopController {
  
  private static final int DEFAULT_DISPATCHER_PORT = 5698;
  private static final String DISPATCHER_PORT_PARAM_LONG = "port";
  private static final String DISPATCHER_PORT_PARAM_SHORT = "p";
  private static final String LOG_LABEL = "V2C-LDC";
  
  /**
   * Entry-point.
   * 
   * @param args the program arguments
   */
  public static void main(String[] args) {
    final Logger logger = new Logger();
    
    try {
      Options options = new Options();
      options.addOption(DISPATCHER_PORT_PARAM_SHORT, DISPATCHER_PORT_PARAM_LONG, true,
          "Specifies the default dispatcher port. Default = " + DEFAULT_DISPATCHER_PORT);
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);
      
      int port;
      
      if(cmd.hasOption(DISPATCHER_PORT_PARAM_LONG)) {
        logger.onDebug(LOG_LABEL, "Picked up an explicitly specified port.");
        port = Integer.parseInt(cmd.getOptionValue(DISPATCHER_PORT_PARAM_LONG));
      } else {
        logger.onDebug(LOG_LABEL, "No port specified, falling back to default.");
        port = DEFAULT_DISPATCHER_PORT;
      }
      
      logger.onInfo(LOG_LABEL, String.format("Intending to connect to dispatcher on port %1$d.", port));
      
    } catch(Exception e) {
      logger.onError(LOG_LABEL, "Exception thrown: "
          + (e.getMessage() == null ? "Unknown." : e.getMessage()));
    }
    
    logger.onInfo(LOG_LABEL, "Program terminated.");
  }
  
}
