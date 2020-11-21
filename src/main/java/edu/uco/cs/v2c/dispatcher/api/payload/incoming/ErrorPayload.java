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
import edu.uco.cs.v2c.dispatcher.api.payload.incoming.IncomingPayload.IncomingAction;

/**
 * Encapsulates the outgoing {@link IncomingAction#WEBSOCKET_ERROR} payload.
 * 
 * @author Caleb L. Power
 */
public class ErrorPayload extends IncomingPayload {
  
  private static final String CAUSE_VAR = "cause";
  private static final String INFO_VAR = "info";
  
  private JSONObject cause = null;
  private String info = null;
  
  /**
   * Overloaded constructor to instantiate the payload.
   * 
   * @param raw the raw JSON
   * @throws PayloadHandlingException if the payload was invalid
   */
  public ErrorPayload(JSONObject raw) throws PayloadHandlingException {
    super(raw, IncomingAction.WEBSOCKET_ERROR);
    
    try {
      cause = raw.getJSONObject(CAUSE_VAR);
      info = raw.getString(INFO_VAR);
    } catch(JSONException e) {
      throw new PayloadHandlingException(action, e, raw);
    }
  }
  
  /**
   * Retrieves the cause (the bad payload).
   * 
   * @return the cause or <code>null</code> if it didn't exist or
   *         wasn't a well-formed {@link JSONObject}
   */
  public JSONObject getCause() {
    return cause;
  }
  
  /**
   * Retrieves any information about the error.
   * 
   * @return the additional information
   */
  public String getInfo() {
    return info;
  }
  
}
