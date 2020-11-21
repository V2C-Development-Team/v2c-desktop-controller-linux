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
package edu.uco.cs.v2c.dispatcher.api.payload.incoming;

import org.json.JSONException;
import org.json.JSONObject;

import edu.uco.cs.v2c.dispatcher.api.payload.PayloadHandlingException;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.IncomingPayload.IncomingAction;

/**
 * Encapsulates the outgoing {@link IncomingAction#ROUTE_COMMAND} payload.
 * 
 * @author Caleb L. Power
 */
public class RouteCommandPayload extends IncomingPayload {
  
  private static final String COMMAND_VAR = "command";
  private static final String RECIPIENT_VAR = "recipient";
  
  private String command = null;
  private String recipient = null;
  
  /**
   * Overloaded constructor to instantiate the payload.
   * 
   * @param raw the raw JSON
   * @throws PayloadHandlingException if the payload was invalid
   */
  public RouteCommandPayload(JSONObject raw) throws PayloadHandlingException {
    super(raw, IncomingAction.ROUTE_COMMAND);
    
    try {
      command = raw.getString(COMMAND_VAR);
      recipient = raw.getString(RECIPIENT_VAR);
    } catch(JSONException e) {
      throw new PayloadHandlingException(action, e, raw);
    }
  }
  
  /**
   * Retrieves the command to be routed.
   * 
   * @return the command
   */
  public String getCommand() {
    return command;
  }
  
  /**
   * Retrieves the unique identifier of the recipient.
   * 
   * @return the recipient
   */
  public String getRecipient() {
    return recipient;
  }
  
}
