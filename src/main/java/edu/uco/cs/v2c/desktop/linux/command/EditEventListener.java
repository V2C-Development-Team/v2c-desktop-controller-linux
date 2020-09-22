package edu.uco.cs.v2c.desktop.linux.command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.uco.cs.v2c.desktop.linux.ui.EditCommand;

public class EditEventListener implements ActionListener {

	EditCommand editCommand;

	public EditEventListener(EditCommand editCommand) {
		this.editCommand = editCommand;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = editCommand.getExecuteTextString();
		// System.out.println(e);
		// String commandString = (String) e.getSource();
		// System.out.println(commandString);

		TerminalCommandJava tempCommand = new TerminalCommandJava();
		// tempCommand.ExecuteCommand("ping google.com");
		tempCommand.ExecuteCommand(command);
	}

}
