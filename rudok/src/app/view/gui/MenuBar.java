package app.view.gui;




import javax.swing.*;
import java.awt.*;


public class MenuBar extends JMenuBar {


    public MenuBar(){
        //making menu fields
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenu editDocument = new JMenu("Edit");
        Color color = new Color(0x528B8B);
        file.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editDocument.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(file);
        add(editDocument);
        add(help);

        //setting color of menu bar component
        setBackground(color);


        //adding actions to menu fields(actions act like JMenuItem)

        //setting cursor for JMenuItems
        JMenuItem item = file.add(MainFrame.getInstance().getActionManager().getNewAction());
        item.setBackground(color);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        item = editDocument.add(MainFrame.getInstance().getActionManager().getSetAuthorAction());
        item.setBackground(color);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        item = editDocument.add(MainFrame.getInstance().getActionManager().getEditDocumentAction());
        item.setBackground(color);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        item = help.add(MainFrame.getInstance().getActionManager().getInfoAction());
        item.setBackground(color);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        setVisible(true);
    }
}
