package edu.uco.cs.v2c.desktop.linux.model;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import org.json.*;

public class CommandDataTable extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private String filePath = "src/main/java/edu/uco/cs/v2c/desktop/linux/model/saveCommands.json";
    private String[] columnNames = { "Command Name", "Description", "Activation Phrase", "Execute" };
    private String[][] data = readJsonCommandData(filePath);

    public CommandDataTable() {
        data = readJsonCommandData(filePath);
        writeJsonCommandData(filePath);
        // String[][] test =
        // readJsonCommandData("src/main/java/edu/uco/cs/v2c/desktop/linux/model/saveCommands.json");
    }

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

    public void setValue(int rowIndex, String[] updatedData) {
        data[rowIndex] = updatedData;
        writeJsonCommandData(filePath);
    }

    private void writeJsonCommandData(String file) {
        try {
            JSONObject obj = new JSONObject();
            JSONArray commands = new JSONArray();
            for (int i = 0; i < data.length; i++) {
                JSONObject commandDetails = new JSONObject();
                for (int c = 0; c < columnNames.length; c++) {
                    // JSONObject commandEntry = new JSONObject();
                    commandDetails.put(columnNames[c], data[i][c]);
                }
                commands.put(commandDetails);
            }
            obj.put("commands", commands);
            String jsonString = obj.toString();
            Path fileName = Path.of(file);
            Files.writeString(fileName, jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[][] readJsonCommandData(String file) {
        try {
            Path fileName = Path.of(file);
            String jsonString = Files.readString(fileName);
            JSONObject obj = new JSONObject(jsonString);
            JSONArray commands = obj.getJSONArray("commands");
            String[][] data = new String[commands.length()][columnNames.length];
            for (int i = 0; i < commands.length(); i++) {
                for (int c = 0; c < columnNames.length; c++) {
                    String x = commands.getJSONObject(i).getString(columnNames[c]);
                    data[i][c] = x;
                }
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
