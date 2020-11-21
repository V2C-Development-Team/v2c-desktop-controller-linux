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
package edu.uco.cs.v2c.dispatcher.api.payload.outgoing;

import org.json.JSONObject;

import edu.uco.cs.v2c.dispatcher.api.payload.MalformedPayloadException;
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.IncomingPayload.IncomingAction;

/**
 * An outgoing payload to be sent over a WebSocket for the purposes of
 * interacting with the chatroom functionality.
 * 
 * @author Caleb L. Power
 */
public abstract class OutgoingPayload {
  
  /**
   * Actions denoting the purpose of an outgoing message.
   * Such messages are generally sent by this system.
   * 
   * @author Caleb L. Power
   */
  public static enum OutgoingAction {
    
    /**
     * Indicates that the payload regards some listener that is requesting to
     * be registered on the network.
     */
    REGISTER_LISTENER,
    
    /**
     * Indicates that the payload regards some listener that is requesting to
     * be deregistered on the network.
     */
    DEREGISTER_LISTENER,
    
    /**
     * Indicates that the payload contains some message that needs to be routed
     * to another module in the system. The message in question will generally
     * regard some application statistic or event.
     */
    DISPATCH_MESSAGE,
    
    /**
     * Indicates that the payload contains some raw partial voice transcription
     * that potentially needs to be routed to some additional module.
     */
    DISPATCH_COMMAND,
    
    /**
     * Indicates that the payload contains some default configuration for a
     * particular application in question.
     */
    REGISTER_CONFIGURATION,
    
    /**
     * Indicates that the payload contains some updated configuration that
     * another module should apply.
     */
    UPDATE_CONFIGURATION,
    
    /**
     * Indicates a keepalive response, in direct response to
     * {@link IncomingAction#HEARTBEAT}.
     */
    HEARTBEAT_ACK
    
  }
  
  protected static final String ACTION_VAR = "action";
  
  protected OutgoingAction action = null;
  
  protected OutgoingPayload(OutgoingAction action) {
    this.action = action;
  }
  
  /**
   * Retrieves the {@link OutgoingAction} if it exists.
   * 
   * @return the appropriate {@link OutgoingAction}
   */
  public OutgoingAction getAction() {
    return action;
  }
  
  /**
   * Serializes the encapsulated payload into a JSON object that can be sent
   * over the WebSocket.
   * 
   * @return a {@link JSONObject}
   * @throws MalformedPayloadException if payload is invalid upon serialization
   */
  public abstract JSONObject serialize() throws MalformedPayloadException;
  
  /**
   * {@inheritDoc}
   */
  @Override public String toString() {
    try {
      return serialize().toString();
    } catch(MalformedPayloadException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
}
