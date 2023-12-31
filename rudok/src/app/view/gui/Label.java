package app.view.gui;


import app.view.repository.DocumentView;
import app.view.repository.FileView;
import app.view.repository.WorkspaceView;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Label extends JPanel   {

    Button currFile;
    Button currDocument;
    Button backToWorkspace;
    Button currPage;
    JPanel panel;


    public Label(WorkspaceView workspace){
        super(new BorderLayout());
        setPreferredSize(new Dimension(0,40));
        setBorder(new EmptyBorder(10,30,10,50));
        setBackground(new Color(0xC6F9F4));

        backToWorkspace = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                workspace.setFileExplorer();
            }
        };
        backToWorkspace.setIcon(loadIcon("../repository/images/backToWorkspace.png"));
        backToWorkspace.setToolTipText("Go back to this workspace");

        currFile = new Button(null);
        currFile.setHorizontalAlignment(SwingConstants.CENTER);
        currFile.setIcon(loadIcon("../repository/images/open-folder.png"));
        currFile.setOpaque(true);

        currDocument = new Button(null);
        currDocument.setHorizontalAlignment(SwingConstants.CENTER);
        currDocument.setToolTipText("Currently opened presentation");
        currDocument.setIcon(loadIcon("../repository/images/presentation.png"));
        currDocument.setOpaque(true);

        currPage = new Button(null);
        currPage.setHorizontalAlignment(SwingConstants.CENTER);
        currPage.setOpaque(true);

        panel = new JPanel();
        panel.setBackground(new Color(0xC6F9F4));
        BoxLayout box = new BoxLayout(panel,BoxLayout.X_AXIS);
        panel.setLayout(box);

    }

    public void setCurrPath(){
       panel.removeAll();

       JPanel view = WorkspaceView.getCurrentlyOpened();

       if(view instanceof FileView){
           panel.add(backToWorkspace);
           panel.add(Box.createHorizontalStrut(25));

           currFile = new Button(null){
               @Override
               public void mouseClicked(MouseEvent e) {
                   super.mouseClicked(e);

                   TreeItem item = MainFrame.getInstance().getITree().findItemByModel(((FileView)view).getModel());
                   MainFrame.getInstance().getITree().getTreeView().setSelectionPath(
                           new TreePath(item.getPath()));
               }
           };
           currFile.setToolTipText("You are currently in this file");
           currFile.setText(((FileView)view).getModel().getName());
           currFile.setIcon(loadIcon("../repository/images/open-folder.png"));
           currFile.setForeground(Color.cyan);
           panel.add(currFile);
       }

       else if(view instanceof DocumentView){

           panel.add(backToWorkspace);

           panel.add(Box.createHorizontalStrut(25));

           currFile = new Button(null){
               @Override
               public void mouseClicked(MouseEvent e) {
                   super.mouseClicked(e);
                   if(WorkspaceView.getCurrentlyOpened() instanceof DocumentView){
                       DocumentView doc = (DocumentView) WorkspaceView.getCurrentlyOpened();
                       doc.getParentView().display();
                   }
               }
           };
           currFile.setText(((DocumentView)view).getParentView().getModel().getName());
           currFile.setToolTipText("Go back to file of this presentation");
           currFile.setIcon(loadIcon("../repository/images/open-folder.png"));
           panel.add(currFile);
           panel.add(Box.createHorizontalStrut(25));

           currDocument.setText(((DocumentView)view).getModel().getName());
           currDocument.setForeground(Color.cyan);
           panel.add(currDocument);
       }


        add(panel, BorderLayout.CENTER);
    }

    private Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("MyAbstractAction - load icon failed");
        return icon;
    }

}
