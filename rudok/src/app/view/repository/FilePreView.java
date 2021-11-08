package app.view.repository;

import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FilePreView extends JPanel implements MouseListener {


    private FileView myView;

    public FilePreView(FileView file){
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
        TreeItem item;
        setBackground(Color.pink);
        item = MainFrame.getInstance().getITree().findItemByModel(myView.getModel());
        MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));

        if(e.getClickCount() == 2){
            myView.getParentView().explorerMode(myView);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


        setBackground(Color.GREEN);

    }

    @Override
    public void mouseExited(MouseEvent e) {

        setBackground(Color.yellow);
    }
}
