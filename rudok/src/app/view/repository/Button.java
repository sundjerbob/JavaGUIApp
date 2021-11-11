package app.view.repository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {

    public Button(){
        setBackground(Color.CYAN.darker());
        setRolloverEnabled(false);
        setFocusPainted(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(Color.red);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackground(Color.cyan);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(Color.cyan);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.cyan.darker());
    }
}
