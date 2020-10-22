/*
 * Copyright (c) 2020 Caleb L. Power. All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.uco.cs.v2c.desktop.linux.dfaparser;


public interface StateListener {
	  
	  /**
	   * Performs some action when a new state is reached.
	   * 
	   * @param state the new state
	   */
	  public void onState(State state);
	  
	  /**
	   * Performs some action when a terminal state is reached.
	   * 
	   * @param state the terminal state
	   */
	  public void onTerminalState(State state);
	  
	}
