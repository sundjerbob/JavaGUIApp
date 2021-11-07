package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.File;
import app.model.repository.Workspace;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class WorkspaceView extends JPanel implements ISubscriber {

    public JScrollPane pane;
    private Workspace model;
    private ArrayList<FileView> files;
    private JPanel fileExplorer;



    public WorkspaceView(Workspace root){
        super(new BorderLayout());
        model = root;
        root.addSubscriber(this);
        fileExplorer = new JPanel();
        fileExplorer.setLayout(null);
        fileExplorer.setBackground(Color.yellow);
        add(fileExplorer);
    }

    public void explorerMode(){


        fileExplorer.removeAll();
        if (files == null || files.size() == 0) {
            return;
        }

        int colNum = 5; //layout will have 5 previews per row
        int rowNum = model.getChildren().size() / 5 + 1;//calculating how many rows we need to display all previews

        int w  = (fileExplorer.getWidth() - 40) / colNum, h = (int)(w * 1.3); // w is with of FilePreView and h is the height
        int x = fileExplorer.getLocation().x + 20, y = fileExplorer.getLocation().y + 20; // location where would be the first preview(upper left corner of WorkspaceView)

        fileExplorer.setPreferredSize(new Dimension(getWidth() - 20,
                rowNum  * h + 40));




        FilePreView cur; //variable to add to our layout
        int index = 0; //variable for indexing every FileView from files array



        for( int i = 0; i < rowNum; i++ ){

            for(int j = 0; j < colNum; j++){
                if(index == files.size())
                    break;
                cur = new FilePreView(files.get(index++));
                cur.setBounds(x + w * j, y + h * i, w, h);
                fileExplorer.add(cur);
            }

        }


        if (pane != null)
            remove(pane);

        pane = new JScrollPane(fileExplorer);
        pane.setHorizontalScrollBar(null);
        add(pane, BorderLayout.CENTER);
        updateUI();
    }






    @Override
    public void update(Object notification) {

        Notification myNotification = (Notification) notification;


        if(myNotification.getType() == NotificationType.ADD_ACTION){
            if(files == null)
                files = new ArrayList<FileView>();
            files.add(new FileView((File)myNotification.getNotificationObject(), this));
            explorerMode();

        }





    }


    public void removeFile(FileView toRemove){
        files.remove(toRemove);
        explorerMode();
    }
}



