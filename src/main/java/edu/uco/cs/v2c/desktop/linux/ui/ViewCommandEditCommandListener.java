package edu.uco.cs.v2c.desktop.linux.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import edu.uco.cs.v2c.desktop.linux.model.CommandDataTable;

public class ViewCommandEditCommandListener extends MouseAdapter {

    private JTable commandTable;

   // public ViewCommandEditCommandListener(ViewCommand viewcommand ){

    public ViewCommandEditCommandListener(JTable table ){
        this.commandTable = table;
    }

    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int click = event.getClickCount();
        int selectedRow = commandTable.rowAtPoint(point);
        int selectedColumn = commandTable.columnAtPoint(point);
        //AbstractTableModel model = (AbstractTableModel)event.getSource();
        //String columnName = model.getColumnName(selectedColumn);
        //Object data = model.getValueAt(selectedRow, selectedColumn);
        //System.out.println("Column name = [" + columnName + "], Data = [" + data + "] clicked twice");
        // open new window

        if (click == 2){
            JFrame window1 = new JFrame();
            window1.setLocation(400, 100);
            var editCommand = new EditCommand(window1, "" + selectedRow, "" + selectedColumn));
            editCommand.init(); 
            window1.pack();
            window1.setVisible(true);           
            System.out.println("Row [" + selectedRow + "][" + selectedColumn + "] clicked twice");
            
        } else{
            System.out.println("Row [" + selectedRow + "][" + selectedColumn + "] clicked once");
        }
        
         
        // do your real thing here...
    }
}