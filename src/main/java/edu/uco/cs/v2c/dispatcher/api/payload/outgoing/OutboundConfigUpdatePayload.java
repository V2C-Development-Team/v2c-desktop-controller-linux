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
 * Encapsulates the incoming {@link OutgoingAction#UPDATE_CONFIGURATION} payload.
 * 
 * @author Caleb L. Power
 */
public class OutboundConfigUpdatePayload extends OutgoingPayload {
  
  private static final String APP_VAR = "app";
  private static final String CONFIG_VAR = "config";
  
  private JSONObject config = null;
  private String app = null;
  
  /**
   * Instantiates the UPDATE_CONFIGURATION payload.
   */
  public OutboundConfigUpdatePayload() {
    super(OutgoingAction.UPDATE_CONFIGURATION);
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
  public OutboundConfigUpdatePayload setApp(String app) {
    this.app = app;
    return this;
  }
  
  /**
   * Retrieves the new configuration that the application should load.
   * 
   * @return the configuration JSON object
   */
  public JSONObject getConfig() {
    return config;
  }
  
  /**
   * Sets the new configuration that the application should load.
   * 
   * @param config the config
   * @return this payload
   */
  public OutboundConfigUpdatePayload setConfig(JSONObject config) {
    this.config = config;
    return this;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public JSONObject serialize() throws MalformedPayloadException {
    if(app == null || config == null)
      throw new MalformedPayloadException(action, "Invalid payload.");
    
    return new JSONObject()
        .put(ACTION_VAR, action)
        .put(APP_VAR, app)
        .put(CONFIG_VAR, config);
  }
  
}
