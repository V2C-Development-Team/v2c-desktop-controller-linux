package edu.uco.cs.v2c.desktop.linux.command;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class KeyboardRobot {

	private Robot robot;

	public KeyboardRobot() throws AWTException {
		this.robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);
	}

	public void typeTest() {
		robot.delay(4000);
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(KeyEvent.VK_RIGHT);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		robot.keyRelease(KeyEvent.VK_RIGHT);
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
}
