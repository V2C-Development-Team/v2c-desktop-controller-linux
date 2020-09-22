package edu.uco.cs.v2c.desktop.linux.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import edu.uco.cs.v2c.desktop.linux.model.CommandDataTable;

public class ViewCommand {

	private JFrame window;
	private JTable jTable;
	private JScrollPane jScrollPane;
	private Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

	public ViewCommand(JFrame window) {
		this.window = window;
	}

	public void init() {
		Container cp = window.getContentPane();

		// JPanel northPanel = new JPanel();
		// northPanel.setBorder(emptyBorder);
		// cp.add(BorderLayout.CENTER, northPanel);

		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(emptyBorder);
		jTable = new JTable(new CommandDataTable());
		jScrollPane = new JScrollPane(jTable);
		jTable.setFillsViewportHeight(true);
		centerPanel.add(jScrollPane);
		cp.add(BorderLayout.CENTER, centerPanel);

		// JPanel southPanel = new JPanel();
		// southPanel.setBorder(emptyBorder);
		// cp.add(BorderLayout.CENTER, southPanel);
	}
}