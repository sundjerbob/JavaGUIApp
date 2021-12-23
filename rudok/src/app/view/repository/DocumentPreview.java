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

public class DocumentPreview extends JPanel  {

    private final DocumentView myView;


    public DocumentPreview(DocumentView document){

        super(new BorderLayout());

        myView = document;
        setBorder(new EmptyBorder(40,40,40,40));
        setBackground(Color.cyan.darker());

        JPanel p = new JPanel(new BorderLayout());
        add(p,BorderLayout.CENTER);

        JLabel label = new JLabel(myView.getModel().getName(),SwingConstants.CENTER);
        label.setFont(new Font(label.getFont().getName(),Font.BOLD,15));
        add(label,BorderLayout.SOUTH);


        Button shortcut = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {

                TreeItem item = MainFrame.getInstance().getITree().findItemByModel(myView.getModel());
                MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));

                if(e.getClickCount() == 2){
                    myView.getParentView().getParentView().display(myView);
                }
            }
        };

        URL url = getClass().getResource("images/presentationShortcut.png");
        if(url != null)
            shortcut.setIcon(new ImageIcon(url));
        shortcut.setBorderPainted(false);


        p.add(shortcut,BorderLayout.CENTER);
    }


}
