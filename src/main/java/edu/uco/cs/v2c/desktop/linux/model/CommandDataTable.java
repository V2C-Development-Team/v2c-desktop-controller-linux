package edu.uco.cs.v2c.desktop.linux.model;

import javax.swing.table.AbstractTableModel;

public class CommandDataTable extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    
    private String[] columnNames = { "Command Name", "Activation Phrase", "Execute", "Description" };
    private String[][] data = {
            { "Play Sound", "play chord", "play -q -n synth 2 pluck C5", "Plays a chord when you say play chord" },
            { "Launch Browser", "launch browser", "firefox", "Launches firefox browser when you say launch browser" },
            { "Glances", "glances", "glances", "Launches glances in terminal" } };

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    public Object getRowAt(int rowIndex) {
        return data[rowIndex];
    }

    public void setValue(int rowIndex, String[] updatedData){
        data[rowIndex] = updatedData;
    }
}
