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
package edu.uco.cs.v2c.desktop.linux.command;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import edu.uco.cs.v2c.desktop.linux.log.Logger;

import java.lang.reflect.Field;

public class KeyboardRobot {

	private static Robot robot;
	private int standardDelayPeriod;
	private static final int STANDARD_DELAY = 100;
	private static final String LOG_LABEL = "KEYBOARDROBOT";
	Map<String, Integer> keyCodeToInt = new HashMap<String, Integer>();
	Map<Integer, String> intToKeyCode = new HashMap<Integer, String>();
	

	public int keyStringToInt(String keyString) {
		int keyInt = this.keyCodeToInt.get(keyString);
		if (keyInt != 0) {
			return keyInt;
		} else {
			return -1;
		}
	}

	public String intToKeyString(int keyInt) {
		String keyString = this.intToKeyCode.get(keyInt);
		if (keyString == "") {
			return "Not Found";
		} else {
			return keyString;
		}

	}

	public void LoadKeyEvent() {
		try {
			System.out.println("Loading key events");
			Field[] fields = KeyEvent.class.getFields();
			// System.out.println(fields.length);
			for (int i = 0; i < fields.length; i++) {

				String fieldName = fields[i].getName();

				if (fieldName.startsWith("VK")) {
					int fieldValue = fields[i].getInt(null);
					// System.out.println("Key: " + fieldName + " " + fieldValue + " " + i);

					keyCodeToInt.put(fieldName, fieldValue);
					intToKeyCode.put(fieldValue, fieldName);
				}

			}
			System.out.println("Loaded key events");

		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}

	}

	public KeyboardRobot() throws AWTException {
		this.LoadKeyEvent();
		this.robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);
		this.setStandardDelayPeriod(STANDARD_DELAY);
	}

	public void pressString(String keyToPress) {
		int keyToPressInt = this.keyStringToInt(keyToPress);
		if (keyToPressInt != -1) {
			robot.keyPress(keyToPressInt);
		} else {
			System.out.println("Command string not found");
		}
	}

	public void releaseString(String keyToPress) {
		int keyToPressInt = this.keyStringToInt(keyToPress);
		if (keyToPressInt != -1) {
			robot.keyRelease(keyToPressInt);
		} else {
			System.out.println("Command string not found");
		}
	}

	public void snapWindowLeft() {
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_LEFT);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_LEFT);
	}

	public void snapWindowRight() {
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_RIGHT);
	}

	public void leftMouseClick() {
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(200);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(200);
	}

	public void type(int i) {
		robot.delay(40);
		robot.keyPress(i);
		robot.keyRelease(i);
	}

	public void type(String s) {
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)
				code = code - 32;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}
	
	public static void tab() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_TAB);
			robot.releaseKey(KeyEvent.VK_TAB);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not hit Tab");
		}
		
	}
	
    public static void holdAlt() {
    	KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_ALT);
			
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not hold Alt");
		}
    	
    }
    public static void releaseAlt() {
    	KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			
			robot.releaseKey(KeyEvent.VK_ALT);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not release alt");
		}
    }
    
    public static void holdShift() {
    	KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_SHIFT);
			
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not hold Shift");
		}
    	
    }
    public static void releaseShift() {
    	KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			
			robot.releaseKey(KeyEvent.VK_SHIFT);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not release Shift");
		}
    }
    
    public static void copy() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_CONTROL);
			robot.holdKey(KeyEvent.VK_C);
			robot.releaseKey(KeyEvent.VK_C);
			robot.releaseKey(KeyEvent.VK_CONTROL);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not copy");
		}
	}
    
    public static void paste() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_CONTROL);
			robot.holdKey(KeyEvent.VK_V);
			robot.releaseKey(KeyEvent.VK_V);
			robot.releaseKey(KeyEvent.VK_CONTROL);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not copy");
		}
	}
  
	
	public static void maximize() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_WINDOWS);
			robot.holdKey(KeyEvent.VK_UP);
			robot.releaseKey(KeyEvent.VK_UP);
			robot.releaseKey(KeyEvent.VK_WINDOWS);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not maximize");
		}
	}
	
	
	public static void minimize() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_WINDOWS);
			robot.holdKey(KeyEvent.VK_DOWN);
			robot.releaseKey(KeyEvent.VK_DOWN);
			robot.releaseKey(KeyEvent.VK_WINDOWS);
		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not minimize");
		}
	}
	
	//added to clean up some minor issues with select/copy/paste
	public static void rightArrow() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_RIGHT);
			robot.releaseKey(KeyEvent.VK_RIGHT);

		}
		catch(AWTException e) {
			Logger.onDebug(LOG_LABEL, "Could not Arrow Right");
		}
	}
	
	
	
	public  void holdKey(int i) {
		robot.keyPress(i);
	}
	
	public  void releaseKey(int i) {
		robot.keyRelease(i);
	}

	public void typeNoDelay(int i) {
		robot.keyPress(i);
		robot.keyRelease(i);
	}

	public void standardDelay() {
		robot.delay(this.getStandardDelayPeriod());
	}

	public int getStandardDelayPeriod() {
		return standardDelayPeriod;
	}

	public void setStandardDelayPeriod(int standardDelayPeriod) {
		this.standardDelayPeriod = standardDelayPeriod;
	}

	public Map<Integer, String> getIntToKeyCode() {
		return intToKeyCode;
	}

	public void setIntToKeyCode(Map<Integer, String> intToKeyCode) {
		this.intToKeyCode = intToKeyCode;
	}
}


