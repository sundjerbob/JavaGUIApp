package app.view.repository;

import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FilePreview extends JPanel implements MouseListener {


    private FileView myView;

    public FilePreview(FileView file){
            super(new BorderLayout());
        myView = file;
        setBackground(Color.cyan.darker());
        setBorder(new EmptyBorder(30,30,30,30));
        JPanel p = new JPanel();
        add(p,BorderLayout.CENTER);
        addMouseListener(this);
        add(new JLabel(myView.getModel().getName(),SwingConstants.CENTER),BorderLayout.SOUTH);

    }




    @Override
    public void mouseClicked(MouseEvent e) {
        TreeItem item;
        item = MainFrame.getInstance().getITree().findItemByModel(myView.getModel());
        MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));

        if(e.getClickCount() == 2){

         myView.display();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(Color.gray);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackground(Color.CYAN.darker());
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
