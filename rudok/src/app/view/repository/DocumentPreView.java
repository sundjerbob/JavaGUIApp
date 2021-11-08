package app.view.repository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DocumentPreView extends JPanel implements MouseListener {
    private DocumentView myView;

    public DocumentPreView(DocumentView file){
        super(new BorderLayout());
        myView = file;
        setBackground(Color.yellow);
        setBorder(new EmptyBorder(30,30,30,30));
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        add(p,BorderLayout.CENTER);
        addMouseListener(this);
        add(new JLabel(myView.getModel().getName(),SwingConstants.CENTER),BorderLayout.SOUTH);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        setBackground(Color.DARK_GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(Color.CYAN);
    }
}
