package app.view.repository;



import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Label extends JPanel   {

    Button currFile;
    Button currDocument;
    Button currPage;
    JPanel panel;




    public Label(){
        super(new BorderLayout());
        setPreferredSize(new Dimension(0,40));
        setBorder(new EmptyBorder(10,50,10,50));
        setBackground(new Color(0xC6F9F4));

        currFile = new Button();
        currFile.setHorizontalAlignment(SwingConstants.CENTER);

        currFile.setOpaque(true);




        currDocument = new Button();
        currDocument.setHorizontalAlignment(SwingConstants.CENTER);
        currDocument.setOpaque(true);




        currPage = new Button();
        currPage.setHorizontalAlignment(SwingConstants.CENTER);
        currPage.setOpaque(true);



        panel = new JPanel();
        panel.setBackground(new Color(0xC6F9F4));
        BoxLayout box = new BoxLayout(panel,BoxLayout.X_AXIS);
        panel.setLayout(box);


    }

    public void setCurrPath(){
       removeAll();
       panel.removeAll();

       JPanel currView = WorkspaceView.getCurrentlyOpened();

       if(currView instanceof FileView ){
           currFile.setText(((FileView)currView).getModel().getName());
           panel.add(currFile);

       }
       if(currView instanceof DocumentView){
           currFile = new Button(){
               @Override
               public void mouseClicked(MouseEvent e) {
                   super.mouseClicked(e);
                   if(WorkspaceView.getCurrentlyOpened() instanceof DocumentView){
                       DocumentView doc = (DocumentView) WorkspaceView.getCurrentlyOpened();
                       doc.getParentView().display();
                       TreeItem item = MainFrame.getInstance().getITree().findItemByModel(doc.getParentView().getModel());
                       MainFrame.getInstance().getITree().getTreeView().setSelectionPath(
                               new TreePath(item.getPath()));
                   }
               }
           };
           currFile.setText(((DocumentView)currView).getParentView().getModel().getName());
           panel.add(currFile);

           panel.add(Box.createHorizontalStrut(25));

           currDocument.setText(((DocumentView)currView).getModel().getName());
           panel.add(currDocument);
       }
       if(currView instanceof PageView){
           currFile.setText(((PageView)currView).getParentView().getParentView().getModel().getName());
           panel.add(currFile);

           panel.add(Box.createHorizontalStrut(25));

           currDocument.setText(((PageView)currView).getParentView().getModel().getName());
           panel.add(currDocument);

           panel.add(Box.createHorizontalStrut(25));

           currPage.setText(((PageView)currView).getModel().getName());
           panel.add(currPage);
       }

        add(panel, BorderLayout.CENTER);
    }

}
