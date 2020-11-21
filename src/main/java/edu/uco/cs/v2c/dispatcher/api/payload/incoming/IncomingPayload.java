/*
 * Copyright (c) 2020 Caleb L. Power, Everistus Akpabio, Rashed Alrashed,
 * Nicholas Clemmons, Jonathan Craig, James Cole Riggall, and Glen Mathew.
 * All rights reserved. Original code copyright (c) 2020 Axonibyte Innovations,
 * LLC. All rights reserved. Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
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

/**
 * An incoming payload sent over a WebSocket for the purposes of interacting
 * with the chatroom functionality.
 * 
 * @author Caleb L. Power
 */
public class IncomingPayload {
  
  /**
   * Actions denoting the purpose of an incoming message.
   * Such messages are generally sent by an external user or system.
   * 
   * @author Caleb L. Power
   */
  public static enum IncomingAction {
    
    /**
     * Indicates that the payload contains some message, processed and ready to
     * be sent to the intended recipient.
     */
    ROUTE_MESSAGE,
    
    /**
     * Indicates that the payload contains some command, processed and ready to
     * be sent to the intended recipient. Such a message will have the target-
     * switching activation word sequences stripped.
     */
    ROUTE_COMMAND,
    
    /**
     * Indicates that the payload contains some updated configuration that
     * another module should apply.
     */
    UPDATE_CONFIGURATION,
    
    /**
     * Indicates that the payload regards some error that has occurred,
     * generally (but not necessarily) in response to some action initiated by
     * a third party application.
     */
    WEBSOCKET_ERROR,
    
    /**
     * Indicates a keepalive request-- mostly a NOP, but used to maintain the
     * validity of the WebSocket connection.
     */
    HEARTBEAT
    
  }
  
  protected static final String ACTION_VAR = "action";
  
  protected IncomingAction action = null;
  protected JSONObject raw = null;
  
  protected IncomingPayload(JSONObject raw, IncomingAction expected) throws PayloadHandlingException {
    this.raw = raw;
    
    try {
      if(null == (action = IncomingAction.valueOf(raw.getString("action"))))
        throw new PayloadHandlingException(null, raw);
    } catch(JSONException e) {
      throw new PayloadHandlingException(action, e, raw);
    }
    
    if(this.action != expected)
      throw new PayloadHandlingException(action, "Wrong payload handler.", raw);
  }
  
  /**
   * Retrieves the {@link IncomingAction} if it exists.
   * 
   * @return the appropriate {@link IncomingAction}
   */
  public IncomingAction getAction() {
    return action;
  }
  
  /**
   * Retrieves the raw {@link JSONObject} message.
   * 
   * @return the appropriate {@link JSONObject}
   */
  public JSONObject getRaw() {
    return raw;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public String toString() {
    return getRaw().toString();
  }
  
}
