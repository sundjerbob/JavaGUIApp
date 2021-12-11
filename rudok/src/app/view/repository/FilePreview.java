package app.view.repository;

import app.view.gui.Button;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class FilePreview extends JPanel  {


    private FileView myView;
    private Button shortcut;

    public FilePreview(FileView file){
        super(new BorderLayout());
        myView = file;
        setBorder(new EmptyBorder(50,50,50,50));
        setBackground(Color.CYAN.darker());

        JPanel p = new JPanel(new BorderLayout());
        add(p,BorderLayout.CENTER);

        add(new JLabel(myView.getModel().getName(),SwingConstants.CENTER),BorderLayout.SOUTH);

        shortcut = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                TreeItem item;
                item = MainFrame.getInstance().getITree().findItemByModel(myView.getModel());
                MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));

                if(e.getClickCount() == 2){

                    myView.display();
                }
            }
        };
        shortcut.setIcon(getLabelIcon("images/folderShortcut.png"));
        shortcut.setBorderPainted(false);
        p.add(shortcut, BorderLayout.CENTER);
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
