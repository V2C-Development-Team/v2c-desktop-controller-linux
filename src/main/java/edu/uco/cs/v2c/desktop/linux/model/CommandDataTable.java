package edu.uco.cs.v2c.desktop.linux.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.uco.cs.v2c.desktop.linux.V2CLinuxDesktopController;

public class CommandDataTable extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "saveCommands.json";
    
    private File file = null;
    private String[] columnNames = { "Command Name", "Description", "Activation Phrase", "Execute" };
    private String[][] data = null;

    public CommandDataTable() {
      try {
        String tempDir = System.getProperty("java.io.tmpdir");
        file = new File(tempDir, FILE_NAME);
        boolean alreadyExists = false;
        if(!(alreadyExists = file.exists())) file.createNewFile();
        System.out.println(file.getPath());
        data = readJsonCommandData(!alreadyExists);
        writeJsonCommandData();
        // String[][] test =
        // readJsonCommandData("src/main/java/edu/uco/cs/v2c/desktop/linux/model/saveCommands.json");
      } catch(IOException e) {
        e.printStackTrace();
      }
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
        writeJsonCommandData();
    }

    private void writeJsonCommandData() {
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
            
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.print(jsonString);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[][] readJsonCommandData(boolean readDefault) {
        try {
            String jsonString = V2CLinuxDesktopController.readResource(
                readDefault ? "/" + FILE_NAME : file.getPath());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
