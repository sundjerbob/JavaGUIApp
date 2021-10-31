package app.view.gui;

import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar(Color color){

        //variable for setting toolbar buttons (putting New action)
        JButton b = add(MainFrame.getInstance().getActionManager().getNewAction());

        //New button visual
        b.setBorder(null);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
     // b.setRolloverEnabled(false);

        //separation between buttons and color of the toolbar component
        addSeparator();
        setBackground(color);

        //Info button visual
        b = add(MainFrame.getInstance().getActionManager().getInfoAction());
        b.setBorder(null);
        b.setOpaque(false);
     // b.setRolloverEnabled(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        setVisible(true);

    }


}
