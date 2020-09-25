package edu.uco.cs.v2c.desktop.linux.ui;

import javax.swing.JFrame;

public class LocalUI {
	public void init() {
		
		// XXX commented out to keep from killing connection to dispatcher
		// window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame window2 = new JFrame();
		// XXX commented out to keep from killing connection to dispatcher
		// window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setLocation(800, 100);

		// JFrame window1 = new JFrame();
		// window1.setLocation(400, 100);
		// var editCommand = new EditCommand(window1);
		// editCommand.init();
		var viewCommand = new ViewCommand(window2);
		viewCommand.init();

		//window1.pack();
		//window1.setVisible(true);
		window2.pack();
		window2.setVisible(true);
	}
}