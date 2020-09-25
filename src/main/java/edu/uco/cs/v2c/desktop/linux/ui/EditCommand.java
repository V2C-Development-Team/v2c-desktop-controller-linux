package edu.uco.cs.v2c.desktop.linux.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class EditCommand {

	private JFrame window;
	private JButton saveButton = new JButton("Save");
	private JLabel commandTitleLabel;
	private JTextField commandTitleTextField;
	private JLabel commandDescriptionLabel;
	private JTextField commandDescriptionTextField;
	private JLabel activationPhraseLabel;
	private JTextField activationPhraseTextField;
	private JLabel executeLabel;
	private JTextField executeTextField;
	private String commandTitle;
	private String commandDescription;
	private String activationPhrase;
	private String executeText;

	private Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private JButton executeButton = new JButton("Execute");

	public EditCommand(JFrame window, String commandTitle, String commandDescription, String activationPhrase,
			String executeText) {
		this.window = window;
		this.commandTitle = commandTitle;
		this.commandDescription = commandDescription;
		this.activationPhrase = activationPhrase;
		this.executeText = executeText;
	}

	public void init() {
		Container cp = window.getContentPane();

		// editPanel.setPreferredSize(new Dimension(800, 600));
		// editPanel.setLayout(new GridLayout(3,1));

		commandTitleLabel = new JLabel("Command Name:");
		commandTitleTextField = new JTextField(commandTitle);
		commandDescriptionLabel = new JLabel("Description of Command:");
		commandDescriptionTextField = new JTextField(commandDescription);
		activationPhraseLabel = new JLabel("When I say");
		activationPhraseTextField = new JTextField(activationPhrase);
		executeLabel = new JLabel("Execute Command:");
		executeTextField = new JTextField(executeText);

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
		southPanel.add(saveButton);
		cp.add(BorderLayout.SOUTH, southPanel);

		// add listener
		executeButton.addActionListener(new EditEventListener(this));
	}

	public String getExecuteTextString() {
		return executeTextField.getText();
	}
}