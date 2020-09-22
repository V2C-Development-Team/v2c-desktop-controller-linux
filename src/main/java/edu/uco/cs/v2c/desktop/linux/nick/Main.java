package edu.uco.cs.v2c.desktop.linux.nick;

import javax.swing.JFrame;

import edu.uco.cs.v2c.desktop.linux.nick.view.EditCommand;
import edu.uco.cs.v2c.desktop.linux.nick.view.ViewCommand;

public class Main {
	public static void main(String[] args) {
		JFrame window1 = new JFrame();
		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window1.setLocation(400, 100);
		JFrame window2 = new JFrame();
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setLocation(800, 800);

		var editCommand = new EditCommand(window1);
		editCommand.init();
		var viewCommand = new ViewCommand(window2);
		viewCommand.init();

		window1.pack();
		window1.setVisible(true);
		window2.pack();
		window2.setVisible(true);
	}
}