package edu.uco.cs.v2c.desktop.linux.command;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Field;

public class KeyboardRobot {

	private Robot robot;
	private int standardDelayPeriod;
	private static final int STANDARD_DEAY = 500;
	Map<String,Integer> keyMap = new HashMap<String,Integer>();

	
	public int keyStringToInt(String keyString){
		int keyInt = this.keyMap.get(keyString);
		if( keyInt != 0 ) {
			return keyInt;
		} else {
			return -1;
		}
	}

	public void LoadKeyEvent(){
		try {
			System.out.println("Loading key events");
			Field[] fields = KeyEvent.class.getFields();
			System.out.println(fields.length);
			for (int i = 0; i < fields.length; i++) {
				
				String fieldName = fields[i].getName();
				
				if (fieldName.startsWith("VK")) {
					int fieldValue = fields[i].getInt(null);
					// System.out.println("Key: " + fieldName + " " + fieldValue + " " + i);

					keyMap.put(fieldName,fieldValue);
				}

				// Add the code and name values to the maps
				// keyCodesToNames.put(keyCode, keyName);
				// keyNamesToCodes.put(keyName,keyCode);
			}

		} catch (Exception e) {
			System.out.println(e);
			//TODO: handle exception
		}
		
	}

	public KeyboardRobot() throws AWTException {
		this.LoadKeyEvent();
		this.robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);
		this.setStandardDelayPeriod(STANDARD_DEAY);
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

	public void leftMouseClick()
	{
	  robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	  robot.delay(200);
	  robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	  robot.delay(200);
	}
	
	public void type(int i)
	{
	  robot.delay(40);
	  robot.keyPress(i);
	  robot.keyRelease(i);
	}
  
	public void type(String s)
	{
	  byte[] bytes = s.getBytes();
	  for (byte b : bytes)
	  {
		int code = b;
		// keycode only handles [A-Z] (which is ASCII decimal [65-90])
		if (code > 96 && code < 123) code = code - 32;
		robot.delay(40);
		robot.keyPress(code);
		robot.keyRelease(code);
	  }
	}
	public void standardDelay(){
		robot.delay(this.getStandardDelayPeriod());
	}

	public int getStandardDelayPeriod() {
		return standardDelayPeriod;
	}

	public void setStandardDelayPeriod(int standardDelayPeriod) {
		this.standardDelayPeriod = standardDelayPeriod;
	}
}
