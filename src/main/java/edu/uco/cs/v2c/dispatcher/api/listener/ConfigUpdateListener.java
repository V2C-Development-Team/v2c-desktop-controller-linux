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
package edu.uco.cs.v2c.dispatcher.api.listener;

import edu.uco.cs.v2c.dispatcher.api.payload.incoming.InboundConfigUpdatePayload;

/**
 * Responds to an incoming request for a config update.
 * 
 * @author Caleb L. Power
 */
public interface ConfigUpdateListener {
  
  /**
   * Performs some action when a config update request is detected.
   * 
   * @param payload the incoming payload
   */
  public void onConfigUpdate(InboundConfigUpdatePayload payload);
  
}
