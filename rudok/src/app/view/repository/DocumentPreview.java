package app.view.repository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DocumentPreview extends JPanel implements MouseListener {
    private DocumentView myView;
    private JButton shortcut;

    public DocumentPreview(DocumentView document){
        super(new BorderLayout());

        myView = document;
        shortcut = new JButton();
        setBackground(Color.cyan.darker());
        setBorder(new EmptyBorder(30,30,30,30));
        JPanel p = new JPanel();
        p.setBackground(Color.white);
        add(p,BorderLayout.CENTER);
        addMouseListener(this);
        add(new JLabel(myView.getModel().getName(),SwingConstants.CENTER),BorderLayout.SOUTH);

    }

    @Override
    public void mouseClicked(MouseEvent e) {


        if(e.getClickCount() == 2){

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(Color.gray);
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
        setBackground(Color.CYAN.darker());
    }
}
