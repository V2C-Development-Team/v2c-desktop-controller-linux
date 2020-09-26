package edu.uco.cs.v2c.desktop.linux.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

import edu.uco.cs.v2c.desktop.linux.model.CommandDataTable;

public class EditCommand {

	private JFrame window;
	private CommandDataTable commandDataTable;
	private JButton saveButton = new JButton("Save");
	private JLabel commandTitleLabel;
	private JTextField commandTitleTextField;
	private JLabel commandDescriptionLabel;
	private JTextField commandDescriptionTextField;
	private JLabel activationPhraseLabel;
	private JTextField activationPhraseTextField;
	private JLabel executeLabel;
	private JTextField executeTextField;
	private int rowIndex;
	private String[] fields;

	private Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private JButton executeButton = new JButton("Execute");

	public EditCommand(JFrame window, CommandDataTable commandDataTable, int rowIndex, String[] fields) {
		this.window = window;
		this.commandDataTable = commandDataTable;
		this.rowIndex = rowIndex;
		this.fields = fields;
	}

	public void init() {
		Container cp = window.getContentPane();

		// editPanel.setPreferredSize(new Dimension(800, 600));
		// editPanel.setLayout(new GridLayout(3,1));

		commandTitleLabel = new JLabel("Command Name:");
		commandTitleTextField = new JTextField(fields[0]);
		commandDescriptionLabel = new JLabel("Description of Command:");
		commandDescriptionTextField = new JTextField(fields[1]);
		activationPhraseLabel = new JLabel("When I say");
		activationPhraseTextField = new JTextField(fields[2]);
		executeLabel = new JLabel("Execute Command:");
		executeTextField = new JTextField(fields[3]);

		JPanel northPanel = new JPanel();
		northPanel.setBorder(emptyBorder);
		// northPanel.setLayout(new GridLayout(3,1));
		northPanel.add(commandTitleLabel);
		northPanel.add(commandTitleTextField);
		northPanel.add(commandDescriptionLabel);
		northPanel.add(commandDescriptionTextField);

		cp.add(BorderLayout.NORTH, northPanel);

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		centerPanel.setBorder(emptyBorder);
		centerPanel.setLayout(new GridLayout(3, 1));
		centerPanel.add(activationPhraseLabel);
		centerPanel.add(activationPhraseTextField);
		centerPanel.add(executeLabel);
		centerPanel.add(executeTextField);
		centerPanel.add(executeButton);
		cp.add(BorderLayout.CENTER, centerPanel);

		JPanel southPanel = new JPanel();
		southPanel.setBorder(emptyBorder);
		saveButton.setEnabled(false);
		southPanel.add(saveButton);
		cp.add(BorderLayout.SOUTH, southPanel);

		// add listener
		executeButton.addActionListener(new EditEventListener(this));
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] updatedData = { commandTitleTextField.getText(), commandDescriptionTextField.getText(),
						activationPhraseTextField.getText(), executeTextField.getText(), };
				commandDataTable.setValue(rowIndex, updatedData);
			}
		});
	}

	public String getExecuteTextString() {
		return executeTextField.getText();
	}
}