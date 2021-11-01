package app.view.gui;

import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar(){

        setBackground(Color.cyan.darker());
        //setBackground(new Color(0x528B8B));
        Color c = Color.CYAN;
        //variable for setting toolbar buttons (putting New action)
        JButton b = add(MainFrame.getInstance().getActionManager().getNewAction());


        //New button visual
        //b.setBorder(null);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBackground(c);
     // b.setRolloverEnabled(false);

        //separation between buttons and color of the toolbar component
        addSeparator();

        //Delete button visual
        b = add(MainFrame.getInstance().getActionManager().getDeleteAction());
        b.setFocusPainted(false);

        b.setBackground(c);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addSeparator();

        //Info button visual
        b = add(MainFrame.getInstance().getActionManager().getInfoAction());
        //b.setBorder(null);
        
        b.setBackground(c);
      //b.setRolloverEnabled(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        setVisible(true);

    }


}
