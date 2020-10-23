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


import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Representation of a state in a DFA.
 * 
 * @author Caleb L. Power
 */
public class State {
  
  private boolean done = false;
  private boolean wipe = false;
  private int id = -1;
  private Entry<String, String> setEntry = null;
  private Map<String, State> nextStates = new HashMap<>();
  private State defaultNextState = null;
  private String incomingTransition = null;
  private String saveKey = null;
  
  /**
   * Instantiates a state with an ID.
   * 
   * @param id the unique identifier of the state
   */
  public State(int id) {
    this.id = id;
  }
  
  /**
   * Retrieves the ID of the state.
   * 
   * @return the unique identifier of the state
   */
  public int getID() {
    return id;
  }
  
  /**
   * Determines if this is the last state.
   * 
   * @return <code>true</code> iff this state is an ending state
   */
  public boolean isDone() {
    return done;
  }
  
  /**
   * Sets the flag that determines whether or not this state is the last state.
   * 
   * @param done <code>true</code> iff this state should be a terminating state
   */
  public void setDone(boolean done) {
    this.done = done;
  }
  
  /**
   * Determines whether or not known variables should be wiped at this state.
   * 
   * @return <code>true</code> iff all variables should be wiped at this state
   */
  public boolean doWipe() {
    return wipe;
  }
  
  /**
   * Sets the flag that determines whether or not known variables should be
   * wiped at this state.
   * 
   * @param wipe <code>true</code> iff all variables should be wiped at this
   *        particular state
   */
  public void setWipe(boolean wipe) {
    this.wipe = true;
  }
  
  /**
   * Retrieves the next state, given the transition.
   * 
   * @param transition the transition out of this state
   * @return the next state or <code>null</code> if this is a terminating state
   * @throws Exception if the transition is invalid
   */
  public State getNextState(String transition) throws Exception {
    State state = null;
    
    if(nextStates.containsKey(transition)) // if the transition if explicitly known
      state = nextStates.get(transition);
    else if(defaultNextState != null) // if the transition is implicitly known
      state = defaultNextState;
    else if(done) return null; // if this is a terminating node
    else throw new Exception("Invalid transition."); // if the transition is invalid
    
    state.incomingTransition = transition;
    return state;
  }
  
  /**
   * Sets the default state, for transitions that are not strictly specified.
   * The priority for the default is lower than all other known transitions.
   * 
   * @param state the default next state
   */
  public void setDefaultState(State state) {
    this.defaultNextState = state;
  }
  
  /**
   * Links a transition to an existing state.
   * 
   * @param transition the transition
   * @param state the next state
   * @throws Exception if the transition already has a destination or the
   *         provided state is null
   */
  public void linkState(String transition, State state) throws Exception {
    if(nextStates.containsKey(transition))
      throw new Exception("State already exists.");
    if(state == null)
      throw new Exception("Next state cannot be null.");
    nextStates.put(transition, state);
  }
  
  /**
   * Determines whether or not the state is supposed to explicitly set a value.
   * 
   * @return <code>true</code> iff the state is supposed to set a value
   */
  public boolean hasSetEntry() {
    return setEntry != null;
  }
  
  /**
   * Retrieves the entry that needs to be set during this state. The key
   * presumably is a known string that will be retrieved by the software, and
   * the value will be some known string that indicates some state.
   * 
   * @return the entry, with a string key and a string value
   */
  public Entry<String, String> getSetEntry() {
    return setEntry;
  }
  
  /**
   * Sets the entry that needs to be set during this state. The key presumably
   * is a known string that will be retrieved by the software (perhaps in a
   * map or something), and the value will be some known string that indicates
   * some arbitrary state.
   * 
   * @param key the key of the entry
   * @param value the explicit value of the entry
   * @throws Exception if an entry was already set
   *         or an oracle collision was detected
   */
  public void setSetEntry(String key, String value) throws Exception {
    if(hasSetEntry()) throw new Exception("Set entry already known.");
    if(hasSaveKey() && saveKey.equals(key))
      throw new Exception("Detected potential collision in oracle.");
    setEntry = new SimpleEntry<>(key, value);
  }
  
  /**
   * Determines whether or not the incoming transition needs to be saved apart
   * from the state.
   * 
   * @return <code>true</code> iff there is a save key that needs to be
   *         associated with the incoming transition
   */
  public boolean hasSaveKey() {
    return saveKey != null;
  }
  
  /**
   * Retrieves the explicitly-defined save key and its associated value, which
   * happens to be the incoming transition. (No transition check is necessary,
   * as all but the first state should have some incoming transition.)
   * 
   * @return the save entry
   * @throws Exception if this state is the first state or no save key was set
   */
  public Entry<String, String> getSaveEntry() throws Exception {
    if(incomingTransition == null)
      throw new Exception("This is the first state; as such, it has no incoming transition.");
    if(!hasSaveKey()) throw new Exception("No save key set.");
    return new SimpleEntry<>(saveKey, incomingTransition);
  }
  
  /**
   * Sets the explicitly-defined save key.
   * 
   * @param key the save key to associate with incoming transitions
   * @throws Exception if a save key was already set
   *         or an oracle collision was detected
   */
  public void setSaveKey(String key) throws Exception {
    if(hasSaveKey()) throw new Exception("Save key already known.");
    if(hasSetEntry() && setEntry.getKey().equals(key))
      throw new Exception("Detected potential collision in oracle.");
    saveKey = key;
  }
  
  /**
   * Retrieves the transition that caused this state.
   * 
   * @return the incoming transition
   */
  public String getIncomingTransition() {
    return incomingTransition;
  }
  
}