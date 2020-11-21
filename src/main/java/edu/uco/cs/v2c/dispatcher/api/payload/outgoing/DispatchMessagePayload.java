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
 * Encapsulates the incoming {@link OutgoingAction#DISPATCH_MESSAGE} payload.
 * 
 * @author Caleb L. Power
 */
public class DispatchMessagePayload extends OutgoingPayload {
  
  private static final String MESSAGE_VAR = "message";
  private static final String RECIPIENT_VAR = "recipient";
  
  private JSONObject message = null;
  private String recipient = null;
  
  /**
   * Instantiates the DISPATCH_MESSAGE payload.
   */
  public DispatchMessagePayload() {
    super(OutgoingAction.DISPATCH_MESSAGE);
  }
  
  /**
   * Retrieves the embedded datum from the message, which will most likely be
   * the bulk of the message
   * 
   * @return the embedded datum
   */
  public JSONObject getMessage() {
    return message;
  }
  
  /**
   * Sets the embedded datum for the message.
   * 
   * @param message the embedded datum
   * @return this payload
   */
  public DispatchMessagePayload setMessage(JSONObject message) {
    this.message = message;
    return this;
  }
  
  /**
   * Retrieves the unique identifier of the listener that the message is
   * intended for.
   * 
   * @return the recipient
   */
  public String getRecipient() {
    return recipient;
  }
  
  /**
   * Sets the unique identifier of the listener that the message is intended for.
   * 
   * @param recipient the recipient
   * @return this payload
   */
  public DispatchMessagePayload setRecipient(String recipient) {
    this.recipient = recipient;
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public JSONObject serialize() throws MalformedPayloadException {
    if(message == null)
      throw new MalformedPayloadException(action, "Invalid payload.");
    
    return new JSONObject()
        .put(ACTION_VAR, action)
        .put(MESSAGE_VAR, message)
        .put(RECIPIENT_VAR, recipient == null ? JSONObject.NULL : recipient);
  }
  
}
