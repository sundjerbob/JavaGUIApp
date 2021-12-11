package app.view.repository;

import app.view.gui.Button;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class DocumentPreview extends JPanel  {

    private DocumentView myView;
    private Button shortcut;

    public DocumentPreview(DocumentView document){

        super(new BorderLayout());

        myView = document;
        setBackground(Color.cyan.darker());
        setBorder(new EmptyBorder(60,60,60,60));
        JPanel p = new JPanel(new BorderLayout());
        add(p,BorderLayout.CENTER);

        shortcut = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                TreeItem item = MainFrame.getInstance().getITree().findItemByModel(myView.getModel());
                MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));
                if(e.getClickCount() == 2){
                    myView.getParentView().getParentView().display(myView);
                }
            }
        };
        shortcut.setIcon(getLabelIcon("images/presentationShortcut.png"));
        shortcut.setBorderPainted(false);


        add(new JLabel(myView.getModel().getName(),SwingConstants.CENTER),BorderLayout.SOUTH);
        p.add(shortcut,BorderLayout.CENTER);
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
