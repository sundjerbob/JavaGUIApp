package app.view.gui;


import app.controller.actions.GraphicsAction;
import app.view.state.AddSlotState;
import app.view.state.DelSlotState;
import app.view.state.SelectState;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



import java.util.ArrayList;

public class DrawToolBar extends JToolBar {

    private ArrayList<JButton> buttons;
    private JButton add;
    private JButton select;
    private JButton delete;
    private JButton color;


    public DrawToolBar(){
        super(VERTICAL);

        add(new Button(MainFrame.getInstance().getActionManager().getChangeModeAction()));

        buttons = new ArrayList<>();

        addSeparator();
        addSeparator();

        add = add(MainFrame.getInstance().getActionManager().getAddSlotStateAction());
        add.setFocusPainted(false);
        add.setBorderPainted(false);
        add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add.setBackground(new Color(210,220,230));
        buttons.add(add);



        select = add(MainFrame.getInstance().getActionManager().getSelectStateAction());
        select.setFocusPainted(false);
        select.setBorderPainted(false);
        select.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        select.setBackground(new Color(210,220,230));
        buttons.add(select);

        delete = add(MainFrame.getInstance().getActionManager().getDelSlotStateAction());
        delete.setFocusPainted(false);
        delete.setBorderPainted(false);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.setBackground(new Color(210,220,230));
        buttons.add(delete);

        addSeparator();
        addSeparator();

        color = add(MainFrame.getInstance().getActionManager().getGraphicsAction());
        color.setFocusPainted(false);
        color.setBorderPainted(false);
        color.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        color.setBackground(new Color(210,220,230));

        setBorder(new EmptyBorder(10,8,0,8));
        setBackground(new Color(210,220,230));

    }

    public void setCurrActive(Object o ){
       if(o instanceof AddSlotState){
           add.setBackground(Color.cyan);
           select.setBackground(new Color(210,220,230));
           delete.setBackground(new Color(210,220,230));
           color.setBackground(new Color(210,220,230));
       }
       if(o instanceof SelectState){
           add.setBackground(new Color(210,220,230));
           select.setBackground(Color.cyan);
           delete.setBackground(new Color(210,220,230));
           color.setBackground(new Color(210,220,230));
       }
       if(o instanceof DelSlotState){
           add.setBackground(new Color(210,220,230));
           select.setBackground(new Color(210,220,230));
           delete.setBackground(Color.cyan);
           color.setBackground(new Color(210,220,230));
       }
        if(o instanceof GraphicsAction){
            add.setBackground(new Color(210,220,230));
            select.setBackground(new Color(210,220,230));
            delete.setBackground(new Color(210,220,230));
            color.setBackground(Color.cyan);
        }
    }
}
