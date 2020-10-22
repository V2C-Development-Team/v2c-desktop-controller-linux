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

import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.json.JSONObject;

import edu.uco.cs.v2c.desktop.linux.dfaparser.State;

/**
 * Loads the states for the DFA.
 * 
 * @author Caleb L. Power
 */
public class Bootloader {
  
  private State initialState = null;
  
  /**
   * Loads states from a serialized string.
   * 
   * @param serialized the raw states
   * @throws Exception if loading was impossible
   */
  public Bootloader(String serialized) throws Exception {
    JSONObject json = new JSONObject(serialized);
    NavigableMap<Integer, State> states = new TreeMap<>();
    
    for(String rawStateKey : json.keySet()) {
      int stateKey = Integer.parseInt(rawStateKey);
      states.put(stateKey, new State(stateKey));
    }
    
    for(Entry<Integer, State> state : states.entrySet()) {
      JSONObject stateObj = json.getJSONObject(Integer.toString(state.getKey()));
      if(stateObj.has("transitions")) {
        JSONObject transitions = stateObj.getJSONObject("transitions");
        
        if(transitions.has("*")) {
          int defaultStateKey = transitions.getInt("*");
          state.getValue().setDefaultState(states.get(defaultStateKey));
        }
        
        for(String transition : transitions.keySet()) {
          if(transition.equals("*")) continue;
          int nextStateKey = transitions.getInt(transition);
          if(!states.containsKey(nextStateKey)) throw new Exception("State not found.");
          state.getValue().linkState(transition, states.get(nextStateKey));
        }
      }
      
      if(stateObj.has("save"))
        state.getValue().setSaveKey(stateObj.getString("save"));
      
      if(stateObj.has("set")) {
        JSONObject setObj = stateObj.getJSONObject("set");
        String setKey = setObj.getString("key");
        String setVal = setObj.getString("value");
        state.getValue().setSetEntry(setKey, setVal);
      }
      
      if(stateObj.has("state")) {
        String stateFlag = stateObj.getString("state");
        if(stateFlag.equalsIgnoreCase("restart"))
          state.getValue().setWipe(true);
        else if(stateFlag.equalsIgnoreCase("done"))
          state.getValue().setDone(true);
        else throw new Exception("Illegal state flag.");
      }
    }
    
    if(states.isEmpty()) throw new Exception("No state was loaded.");
    this.initialState = states.firstEntry().getValue();
  }
  
  /**
   * Retrieves the initial machine
   * 
   * @return the initial state
   */
  public State getInitialState() {
    return initialState;
  }
  
}
