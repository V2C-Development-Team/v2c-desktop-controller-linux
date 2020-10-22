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

import java.awt.AWTException;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map.Entry;

import edu.uco.cs.v2c.desktop.linux.dfaparser.State;
import edu.uco.cs.v2c.desktop.linux.command.KeyboardRobot;
import edu.uco.cs.v2c.desktop.linux.dfaparser.SchemaReader;
import edu.uco.cs.v2c.desktop.linux.dfaparser.StateListener;
import edu.uco.cs.v2c.desktop.linux.log.Logger;
import edu.uco.cs.v2c.desktop.linux.model.NewKeypress;


/**
 * The faux hypervisor implementation to manage the machine and the values
 * that we're ultimately trying to manage.
 * 
 * @author Caleb L. Power
 * @author Jon Craig - Adapted DFA-Observer-Java for use in V2C
 */
public class Hypervisor implements StateListener {
  
  private static final String LOG_LABEL = "HYPERVISOR";
  
  private Bootloader bootloader = null;
  private Machine machine = null;
  
  
  /**
   * Instantiates the hypervisor.
   * @throws Exception if the bootloader couldn't load the file
   */
  public Hypervisor() throws Exception {
    
    
    String rawSchema = SchemaReader.readResource("/state_schema.json");
    bootloader = new Bootloader(rawSchema);
  }
  
  /**
   * Boots the machine.
   * (This can be construed as a hypervisor booting a virtual machine.)
   * 
   * @param machine the machine
   */
  public void boot(Machine machine) {
    this.machine = machine;
    machine.loadState(bootloader.getInitialState());
    
  }
  
  /**
   * {@inheritDoc}
   */
  @Override public void onState(State state) {
    System.out.printf("- Reached new state: %1$d via token %2$s\n", state.getID(), state.getIncomingTransition());
    for(Entry<String, List<String>> entry : machine.getRegister().entrySet()) {
      Logger.onDebug(LOG_LABEL, String.format("--- Key: %1$s\n", entry.getKey()));
      for(String value : entry.getValue())
        Logger.onDebug(LOG_LABEL, String.format("----- Value: %1$s\n", value));
    }
  }
  
  /**
   * {@inheritDoc}
   * 
   */
  
  @Override public void onTerminalState(State state) {
    Logger.onDebug(LOG_LABEL, "--- Hit terminal state!");
    boolean shift = false; //bool for typing characters
    
    
    //if we have action key.
    if(machine.getRegister().containsKey("action")) {
      String action = machine.getRegister().get("action").get(0); //get the action key
      
      	//need to decide the branch of the schema we traveled, i.e. type,move,backspace
      
      	//if it is type
      	if(action.equalsIgnoreCase("type")) {
      		//branch for type
      		
      		//we are expecting a variable list of keypresses under the "keypress" key
      		
      		if(!machine.getRegister().containsKey("keypress")) //if not, we have a problem.
      			Logger.onError(LOG_LABEL, "Observer cannot find the keypress(es).");
      		
      		else {//we found the keypress entries tag
      		     
      			//while there are keypressess left in the list
      				
      			while(machine.getRegister().get("keypress").size() > 0) {
      				try {
      					KeyboardRobot robot = new KeyboardRobot();
      					String keypress = machine.getRegister().get("keypress").remove(0);
      					Logger.onDebug(LOG_LABEL, "type " + keypress);
      					NewKeypress pressToSend = NewKeypress.getKeypress(keypress); //check the ENUM to see if the token = directive for key
      					//#TODO if shift. throw caps flag and next letter caps
      					
      					if(pressToSend.getKeyEvent() == KeyEvent.VK_SHIFT) {
      						shift = true;
      						robot.holdKey(KeyEvent.VK_SHIFT);
      						continue;
      					}
      					robot.holdKey(pressToSend.getKeyEvent());
      					robot.releaseKey(pressToSend.getKeyEvent());
      					if(shift == true) {
      						robot.releaseKey(KeyEvent.VK_SHIFT);
      					}
      					
      				}
      				catch (Exception e) {
      					Logger.onError(LOG_LABEL, "Observer cannot parse the keypress(es).");
      				}
      				
      			}
      			
      		}
      		//end of type branch - reboot the machine 
      	}
      	
      	else if(action.equalsIgnoreCase("move")) {
      		//branch for move
      		
      		// we are expecting a number under the "number key", and direction under "direction"
      		
      		if(!(machine.getRegister().containsKey("number") && machine.getRegister().containsKey("direction"))) {
      			//if not we have a problem.
      			Logger.onError(LOG_LABEL, "Observer cannot find the direction and number to move.");
      		}
      		else {// we found the direction and number tag
      			boolean foundDirection = false; // to check if direction is valid
      			//try to get the number and direction
      			try {
      				String direction = machine.getRegister().get("direction").get(0);
      				KeyboardRobot robot = new KeyboardRobot();
      				//String number = machine.getRegister().get("number").get(0);
      				int parsedNumber = Math.abs(Integer.parseInt(machine.getRegister().get("number").get(0)));
      			    int pressToSend = -1;
      				// we have the number and direction strings, match the direction to the appropriate one.
      				switch(direction.toLowerCase()) {
      				case "up":
      					foundDirection = true;
      					pressToSend = KeyEvent.VK_UP;
      					break;
      				case "down":
      					foundDirection = true;
      					pressToSend = KeyEvent.VK_DOWN;
      					break;
      				case "left":
          				foundDirection = true;
          				pressToSend = KeyEvent.VK_LEFT;
          				break;
      				case "right":
      					foundDirection = true;
      					pressToSend = KeyEvent.VK_RIGHT;
      					break;
      				}
      				if(foundDirection && !(pressToSend == -1)) {
      					Logger.onDebug(LOG_LABEL, "move " + parsedNumber + " spaces " + direction);
      					for(int i = 0; i < parsedNumber; i++) {
      						robot.holdKey(pressToSend);
      						robot.releaseKey(pressToSend);
      					}
      						
      				}else Logger.onError(LOG_LABEL, "Observer found an invalid direction");
      		}
      		catch(Exception e) {
      			Logger.onError(LOG_LABEL, "Observer cannot parse either the number to move or the direction.");
      		}
      		//end of found direction and number tag
      		}
      			
      		//end of move branch - reboot the machine
      	}
      	else if(action.equalsIgnoreCase("backspace")) {
      		//branch for backspace
      		
      		//we are expecting a number of times to backspace
      		if(!(machine.getRegister().containsKey("number"))) {
      			//if not we have a problem
      			Logger.onError(LOG_LABEL, "Observer cannot find the number to backspace.");
      		}
      		else {// we found the number tag
      			
      			try { //try to parse it, so we can avoid a horrible switch statement;
      				KeyboardRobot robot = new KeyboardRobot();
      				int parsedNumber = Math.abs(Integer.parseInt(machine.getRegister().get("number").get(0)));
      				 Logger.onDebug(LOG_LABEL, "backspace " + parsedNumber + " spaces");
      				 for(int i = 0; i < parsedNumber; i++) {
      					 robot.holdKey(KeyEvent.VK_BACK_SPACE);
      					robot.releaseKey(KeyEvent.VK_BACK_SPACE);
      					
      				 }
      			}
      			
      			catch(Exception e) { //parse failed
      				Logger.onError(LOG_LABEL, "Observer cannot parse the number to backspace.");
      			}
      			//end of found number tag
      		}   		
      		//end of backspace branch - reboot the machine
      	}
      	
      	else {//somthing went wrong, we had the action, but could't get the value.
      		Logger.onError(LOG_LABEL, "Observer picked up an invalid action.");
      	}
      
    	
    //end if for containsKey("action")	
    }else { //else we don't have an action key and need to handle problem. 
    	Logger.onDebug(LOG_LABEL, "Observer could not pick out the action.");
    }
    
    //reboot the machine after terminal handling complete.
    Logger.onDebug(LOG_LABEL, "--- Rebooting...");
    machine.loadState(bootloader.getInitialState());
  }
  
}
