package app.view.repository;

import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class DocumentPreview extends JPanel implements MouseListener {

    private DocumentView myView;
    private JLabel shortcut = new JLabel(getLabelIcon("images/presentationShortcut.png"),SwingConstants.CENTER);
    public DocumentPreview(DocumentView document){

        super(new BorderLayout());

        myView = document;
        setBackground(Color.cyan.darker());
        setBorder(new EmptyBorder(30,30,30,30));
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.white);
        add(p,BorderLayout.CENTER);
        addMouseListener(this);
        add(new JLabel(myView.getModel().getName(),SwingConstants.CENTER),BorderLayout.SOUTH);
        p.add(shortcut,BorderLayout.CENTER);
        p.setOpaque(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        TreeItem item = MainFrame.getInstance().getITree().findItemByModel(myView.getModel());
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

    private ImageIcon getLabelIcon(String path){
        URL url = getClass().getResource(path);
        if(url == null) {
            System.out.println("problem");
            return null;
        }
        return new ImageIcon(url);
    }
}
