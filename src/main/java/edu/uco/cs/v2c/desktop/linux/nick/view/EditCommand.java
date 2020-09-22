package edu.uco.cs.v2c.desktop.linux.nick.view;

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

import edu.uco.cs.v2c.desktop.linux.nick.controller.EditEventListener;

public class EditCommand {

	private JFrame window;
	private JButton saveButton = new JButton("Save");
	private JTextField commandText = new JTextField("Command");
	private JTextField executeText = new JTextField("Open browser");
	private Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private JButton executeButton = new JButton("Execute");

	public EditCommand(JFrame window) {
		this.window = window;
	}

	public void init() {
		Container cp = window.getContentPane();

		// editPanel.setPreferredSize(new Dimension(800, 600));
		// editPanel.setLayout(new GridLayout(3,1));

		JPanel northPanel = new JPanel();
		northPanel.setBorder(emptyBorder);
		// northPanel.setLayout(new GridLayout(3,1));

		JLabel whenLabel = new JLabel("When I say");
		northPanel.add(whenLabel);
		northPanel.add(commandText);

		cp.add(BorderLayout.NORTH, northPanel);

		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		centerPanel.setBorder(emptyBorder);
		centerPanel.setLayout(new GridLayout(3, 1));

		JLabel executeLabel = new JLabel("Execute Command:");
		centerPanel.add(executeLabel);
		centerPanel.add(executeText);
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
		return executeText.getText();
	}
}