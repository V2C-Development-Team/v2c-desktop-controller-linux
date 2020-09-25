package edu.uco.cs.v2c.desktop.linux.ui;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class ViewCommandEditCommandListener extends MouseAdapter {

    private JTable commandTable;

    public ViewCommandEditCommandListener(JTable table ){
        this.commandTable = table;
    }

    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int click = event.getClickCount();
        if (click == 2){
            int selectedRow = commandTable.rowAtPoint(point);
            System.out.println("Row" + selectedRow + "clicked twice");
            
            // System.out.println("Table Clicked twice");
        } else{
            System.out.println("Table Clicked once");

        }
        
         
        // do your real thing here...
    }
}