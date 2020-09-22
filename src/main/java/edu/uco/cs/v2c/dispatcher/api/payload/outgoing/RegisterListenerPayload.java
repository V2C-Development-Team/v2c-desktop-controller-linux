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
package edu.uco.cs.v2c.dispatcher.api.payload.outgoing;

import org.json.JSONObject;

import edu.uco.cs.v2c.dispatcher.api.payload.MalformedPayloadException;
import edu.uco.cs.v2c.dispatcher.api.payload.outgoing.OutgoingPayload.OutgoingAction;

/**
 * Encapsulates the incoming {@link OutgoingAction#REGISTER_LISTENER} payload.
 * 
 * @author Caleb L. Power
 */
public class RegisterListenerPayload extends OutgoingPayload {

  private static final String APP_VAR = "app";
  private static final String EAVESDROP_VAR = "eavesdrop";
  
  private boolean eavesdrop = false;
  private String app = null;
  
  /**
   * Instantiates the REGISTER_LISTENER payload.
   */
  public RegisterListenerPayload() {
    super(OutgoingAction.REGISTER_LISTENER);
  }
  
  /**
   * Determines whether or not the listener in question is an eavesdropper.
   * Eavesdroppers are sent messages on the network not explicitly designated to them.
   * 
   * @return <code>true</code> iff the app is an eavesdropper
   */
  public boolean isEavesdropper() {
    return eavesdrop;
  }
  
  /**
   * Sets whether or not the listener in question is an eavesdropper.
   * Eavesdroppers are sent messages on the network not explicitly designated to them.
   * 
   * @param eavesdrop <code>true</code> iff the app is an eavesdropper
   * @return this payload
   */
  public RegisterListenerPayload setEavesdropper(boolean eavesdrop) {
    this.eavesdrop = eavesdrop;
    return this;
  }
  
  /**
   * Retrieves the unique identifier of the application in question.
   * 
   * @return the application's unique identifier
   */
  public String getApp() {
    return app;
  }
  
  /**
   * Sets the unique identifier of the application in question.
   * 
   * @param app the application's unique identifier
   * @return this payload
   */
  public RegisterListenerPayload setApp(String app) {
    this.app = app;
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public JSONObject serialize() throws MalformedPayloadException {
    if(app == null)
      throw new MalformedPayloadException(action, "Invalid payload.");
    
    return new JSONObject()
        .put(ACTION_VAR, action)
        .put(APP_VAR, app)
        .put(EAVESDROP_VAR, eavesdrop);
  }

}
