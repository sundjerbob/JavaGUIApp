package app.view;


import javax.swing.*;
import java.awt.*;


public class MenuBar extends JMenuBar {


    public MenuBar(Color color){
        //making menu fields
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        file.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(file);
        add(help);

        //setting color of menu bar component
        setBackground(color);


        //adding actions to menu fields(actions act like JMenuItem)
        file.add(MainFrame.getInstance().getActionManager().getNewAction());
        help.add(MainFrame.getInstance().getActionManager().getInfoAction());
        setVisible(true);
    }
}
