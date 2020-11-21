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
package edu.uco.cs.v2c.dispatcher.api.payload.outgoing;

import org.json.JSONObject;

import edu.uco.cs.v2c.dispatcher.api.payload.MalformedPayloadException;
import edu.uco.cs.v2c.dispatcher.api.payload.outgoing.OutgoingPayload.OutgoingAction;

/**
 * Encapsulates the incoming {@link OutgoingAction#DISPATCH_COMMAND} payload.
 * 
 * @author Caleb L. Power
 */
public class DispatchCommandPayload extends OutgoingPayload {
  
  private static final String COMMAND_VAR = "command";
  
  private String command = null;
  
  /**
   * Instantiates the DISPATCH_COMMAND payload.
   */
  public DispatchCommandPayload() {
    super(OutgoingAction.DISPATCH_COMMAND);
  }
  
  /**
   * Retrieves the raw transcribed command, presumably sent by a V2C Recognizer.
   * 
   * @return the raw transcribed command
   */
  public String getCommand() {
    return command;
  }
  
  /**
   * Sets the raw transcribed command, to be set by a V2C Recognizer.
   * 
   * @param command the transcribed command
   * @return this payload
   */
  public DispatchCommandPayload setCommand(String command) {
    this.command = command;
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public JSONObject serialize() throws MalformedPayloadException {
    if(command == null)
      throw new MalformedPayloadException(action, "Invalid payload.");
    
    return new JSONObject()
        .put(ACTION_VAR, action)
        .put(COMMAND_VAR, command);
  }
  
}
