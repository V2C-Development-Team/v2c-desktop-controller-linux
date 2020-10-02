import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class KeyboardRobot {

	private Robot robot;

	public KeyboardRobot() {
		this.robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);
	}

	public void typeTest() {
		robot.delay(4000);
		robot.delay(500);
		robot.keyPress(VK_WINDOWS);
	}
}
