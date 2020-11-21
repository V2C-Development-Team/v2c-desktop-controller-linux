/*
 * Copyright (c) 2020 Caleb L. Power, Everistus Akpabio, Rashed Alrashed,
 * Nicholas Clemmons, Jonathan Craig, James Cole Riggall, and Glen Mathew.
 * All rights reserved. Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.uco.cs.v2c.desktop.linux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import edu.uco.cs.v2c.desktop.linux.command.CommandParser;
import edu.uco.cs.v2c.desktop.linux.command.ConfigParser;
import edu.uco.cs.v2c.desktop.linux.log.Logger;
import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.desktop.linux.model.RecognitionStateContext;
import edu.uco.cs.v2c.desktop.linux.net.DispatcherHandler;

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
  private static final String ENABLE_UI_PARAM_LONG = "enable-ui";
  private static final String ENABLE_UI_PARAM_SHORT = "u";
  public static RecognitionStateContext currentRecognitionState;

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
      options.addOption(ENABLE_UI_PARAM_SHORT, ENABLE_UI_PARAM_LONG, false, "Enable User Interface.");
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = parser.parse(options, args);

      String destination = null;

      if (cmd.hasOption(DESTINATION_PARAM_LONG)) {
        Logger.onDebug(LOG_LABEL, "Picked up an explicitly specified destination.");
        destination = cmd.getOptionValue(DESTINATION_PARAM_LONG);
      } else {
        Logger.onDebug(LOG_LABEL, "No port specified, falling back to default.");
        destination = DEFAULT_DESTINATION;
      }

      // LocalUI localUI = new LocalUI();
      if (cmd.hasOption(ENABLE_UI_PARAM_LONG)) {
        System.out.println("you wanted ui but we deleted it sorry");
        // localUI.init();
      }

      Logger.onInfo(LOG_LABEL, String.format("Intending to connect to dispatcher at %1$s.", destination));


      currentRecognitionState = new RecognitionStateContext();
      
      // Dispatcher
      ConfigurationData configurationData = new ConfigurationData();
      CommandParser commandParser = new CommandParser(currentRecognitionState,configurationData);
      ConfigParser configParser = new ConfigParser(configurationData);
      DispatcherHandler handler = DispatcherHandler.build(destination);
      handler.registerCommandListener(commandParser, configParser);

      // catch CTRL + C
      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
          handler.kill();
          try {
            Thread.sleep(1000L);
          } catch (InterruptedException e) {
          }
        }
      });

    } catch (Exception e) {
      Logger.onError(LOG_LABEL, "Exception thrown: " + (e.getMessage() == null ? "Unknown." : e.getMessage()));
      e.printStackTrace();
    }
  }

  /**
   * Reads a resource, preferably plaintext. The resource can be in the classpath,
   * in the JAR (if compiled as such), or on the disk. <em>Reads the entire file
   * at once--so it's probably not wise to read huge files at one time.</em>
   * Eliminates line breaks in the process, so best for source files i.e. HTML or
   * SQL.
   * 
   * @param resource the file that needs to be read
   * @return String containing the file's contents
   */
  public static String readResource(String resource) {
    try {
      if (resource == null)
        return null;
      File file = new File(resource);
      InputStream inputStream = null;
      if (file.canRead())
        inputStream = new FileInputStream(file);
      else
        inputStream = V2CLinuxDesktopController.class.getResourceAsStream(resource);
      if (inputStream == null)
        return null;
      InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      BufferedReader reader = new BufferedReader(streamReader);
      StringBuilder stringBuilder = new StringBuilder();
      for (String line; (line = reader.readLine()) != null;)
        stringBuilder.append(line.trim());
      return stringBuilder.toString();
    } catch (IOException e) {
    }
    return null;
  }

}
