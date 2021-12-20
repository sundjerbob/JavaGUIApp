package app.view.repository;

import app.view.gui.Button;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;

public class FilePreview extends JPanel  {


    private final FileView myView;
    private final Button shortcut;

    public FilePreview(FileView file){
        super(new BorderLayout());
        myView = file;
        setBorder(new EmptyBorder(40,40,40,40));
        setBackground(Color.cyan.darker());

        JLabel label = new JLabel(myView.getModel().getName(),SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(),Font.BOLD,15));
        add(label,BorderLayout.SOUTH);

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
        URL url = getClass().getResource("images/folderShortcut.png");
        if(url == null)
            System.out.println("mmm" +
                    "");
            shortcut.setIcon( new ImageIcon(url));
        shortcut.setBorderPainted(false);
        add(shortcut, BorderLayout.CENTER);
    }


}
