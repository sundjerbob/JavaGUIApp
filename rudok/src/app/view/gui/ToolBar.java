package app.view.gui;

import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar(Color color){

        setBackground(color);

        //variable for setting toolbar buttons (putting New action)
        JButton b = add(MainFrame.getInstance().getActionManager().getNewAction());


        //New button visual
        //b.setBorder(null);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBackground(Color.cyan.darker());
     // b.setRolloverEnabled(false);

        //separation between buttons and color of the toolbar component
        addSeparator();

        //Delete button visual
        b = add(MainFrame.getInstance().getActionManager().getDeleteAction());
        b.setFocusPainted(false);

        b.setBackground(Color.cyan.darker());
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addSeparator();

        //Info button visual
        b = add(MainFrame.getInstance().getActionManager().getInfoAction());
        //b.setBorder(null);
        
        b.setBackground(Color.cyan.darker());
      //b.setRolloverEnabled(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        setVisible(true);

    }


}
