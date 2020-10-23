package edu.uco.cs.v2c.desktop.linux.command;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Field;

public class KeyboardRobot {

	private static Robot robot;
	private int standardDelayPeriod;
	private static final int STANDARD_DEAY = 100;
	Map<String, Integer> keyCodeToInt = new HashMap<String, Integer>();
	Map<Integer, String> intToKeyCode = new HashMap<Integer, String>();
	private static int windowNextCounter = 48;

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
	
	public static void switchTextbox() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.type(KeyEvent.VK_TAB);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to switch text box");
		}
		
		
	}
	
	public static void windowNext() {
		KeyboardRobot robot;
		try {
		if(windowNextCounter == 57) {
			windowNextCounter = 48;
		}
		robot = new KeyboardRobot();
		robot.holdKey(KeyEvent.VK_WINDOWS);
		robot.type(windowNextCounter);
		robot.releaseKey(KeyEvent.VK_WINDOWS);
		windowNextCounter++;
		}
		catch (AWTException e) {
			System.out.println("failed to switch windows");
		}
	}
	
	public static void maximize() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_WINDOWS);
			robot.type(KeyEvent.VK_UP);
			robot.releaseKey(KeyEvent.VK_WINDOWS);
			
		}
		catch(AWTException e) {
			System.out.println("failed to maximize window");
		}
	}
	
	public static void minimize() {
		KeyboardRobot robot;
		try {
			robot = new KeyboardRobot();
			robot.holdKey(KeyEvent.VK_WINDOWS);
			robot.type(KeyEvent.VK_DOWN);
			robot.releaseKey(KeyEvent.VK_WINDOWS);
			
		}
		catch(AWTException e) {
			System.out.println("failed to minimize window");
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


