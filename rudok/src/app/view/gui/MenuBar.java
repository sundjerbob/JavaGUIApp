package app.view.gui;


import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.*;


public class MenuBar extends JMenuBar {


    public MenuBar(Color color){
        //making menu fields
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu editDocument = new JMenu("Edit");

        file.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editDocument.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(file);
        add(editDocument);
        add(help);

        //setting color of menu bar component
        setBackground(new Color(0x528B8B));


        //adding actions to menu fields(actions act like JMenuItem)

        //setting cursor for JMenuItems
        JMenuItem item = file.add(MainFrame.getInstance().getActionManager().getNewAction());
        item.setBackground(new Color(0x528B8B));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        item = editDocument.add(MainFrame.getInstance().getActionManager().getSetAuthorAction());
        item.setBackground(new Color(0x528B8B));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        item = editDocument.add(MainFrame.getInstance().getActionManager().getEditDocumentAction());
        item.setBackground(new Color(0x528B8B));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        item = help.add(MainFrame.getInstance().getActionManager().getInfoAction());
        item.setBackground(new Color(0x528B8B));
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        setVisible(true);
    }
}
