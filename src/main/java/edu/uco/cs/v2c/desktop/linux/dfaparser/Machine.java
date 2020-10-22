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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.uco.cs.v2c.desktop.linux.dfaparser.State;
import edu.uco.cs.v2c.desktop.linux.dfaparser.StateListener;
import edu.uco.cs.v2c.desktop.linux.dfaparser.TokenProcessor;


/**
 * Navigates through the states with the help of provided tokens.
 * 
 * @author Caleb L. Power
 */
public class Machine extends TokenProcessor {
  
  private Map<String, List<String>> register = new HashMap<>();
  private State currentState = null;
  private List<StateListener> listeners = new LinkedList<>();
  
  /**
   * Loads the first state into the machine.
   * 
   * @param state the first state
   */
  public void loadState(State state) {
    this.currentState = state;
    
    if(state != null && state.doWipe())
      register.clear();
  }
  
  /**
   * Pushes a token into the machine and transitions to the appropriate state.
   * 
   * @param token the token
   * @throws Exception if the provided token caused the machine to enter an
   *         illegal state.
   */
  @Override public void pushToken(String token) throws Exception {
    currentState = currentState.getNextState(token);
    
    if(currentState == null)
      throw new Exception("Illegal machine state.");
    
    if(currentState.doWipe()) register.clear();

    if(currentState.hasSetEntry()) {
      Entry<String, String> setEntry = currentState.getSetEntry();
      if(!register.containsKey(setEntry.getKey()))
        register.put(setEntry.getKey(), new LinkedList<>());
      register.get(setEntry.getKey()).add(setEntry.getValue());
    }
      
    if(currentState.hasSaveKey()) {
      Entry<String, String> saveEntry = currentState.getSaveEntry();
      if(!register.containsKey(saveEntry.getKey()))
        register.put(saveEntry.getKey(), new LinkedList<>());
      register.get(saveEntry.getKey()).add(saveEntry.getValue());
    }
    
    for(StateListener listener : listeners) {
      listener.onState(currentState);
      if(currentState.isDone()) listener.onTerminalState(currentState);
    }
  }
  
  /**
   * Registers a state listener.
   * 
   * @param listener the state listener
   */
  public void registerStateListener(StateListener listener) {
    this.listeners.add(listener);
  }
  
  /**
   * Retrieves the machine register.
   * 
   * @return a map representing the register
   */
  public Map<String, List<String>> getRegister() {
    return register;
  }
  
}