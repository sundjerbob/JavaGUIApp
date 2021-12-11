package app.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {

    public Button(Action action){

            super(action);

            if(action != null)
                setHideActionText(true);

            setBackground(Color.CYAN.darker());
            setRolloverEnabled(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(getAction() != null){
            if(getAction().isEnabled())
                setBackground(Color.lightGray);
        }
        else
            setBackground(Color.lightGray);


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(getAction() != null) {
            if (getAction().isEnabled())
                setBackground(Color.cyan);
            else
                setBackground(Color.cyan.darker());
        }
        else
            setBackground(Color.cyan.darker());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(getAction() != null) {
            if (getAction().isEnabled())
                setBackground(Color.cyan);
        }
        else
            setBackground(Color.cyan);
    }

    @Override
    public void mouseExited(MouseEvent e) {
       if(getAction() != null){
           if(getAction().isEnabled())
               setBackground(Color.cyan.darker());
       }
       else
           setBackground(Color.cyan.darker());
    }
}
