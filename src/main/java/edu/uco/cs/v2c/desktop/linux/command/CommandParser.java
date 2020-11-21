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
package edu.uco.cs.v2c.desktop.linux.command;

import edu.uco.cs.v2c.desktop.linux.model.ConfigurationData;
import edu.uco.cs.v2c.desktop.linux.model.RecognitionStateContext;
import edu.uco.cs.v2c.dispatcher.api.listener.CommandListener;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.RouteCommandPayload;

/**
 * Parses incoming commands.
 * 
 * @author Caleb L. Power
 */
public class CommandParser implements CommandListener {
  private RecognitionStateContext currentRecognitionState;
  private ConfigurationData configurationData;
  private static final String DESKTOP_VAR = "desktop";
  private static StateMachine stateMachine;

  /**
   * Instantiates the command parser.
   * 
   * @param configurationData the configurationData model
   */
  public CommandParser(RecognitionStateContext currentRecognitionState, ConfigurationData configurationData) {
    this.currentRecognitionState = currentRecognitionState;
    this.configurationData = configurationData;
    stateMachine = StateMachine.build(configurationData,currentRecognitionState);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void onIncomingCommand(RouteCommandPayload payload) {
    // TerminalCommandJava terminal = new TerminalCommandJava();

    String targetCommand = payload.getCommand();
    
    if (payload.getRecipient().equals(DESKTOP_VAR)) {

    	stateMachine.queue(targetCommand);//handles what mode we are in and passes to the appropriate execute()

    } else {
      System.out.println("=========recipient is not desktop========");
    }

  }
}
