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

    private static final int colNum = 5; //layout will have 5 previews per row

    private  JPanel currView;
    public JScrollPane pane;
    private Workspace model;
    private ArrayList<FileView> files;
    private JPanel fileExplorer;




    public WorkspaceView(Workspace root){

        super(new BorderLayout());
        model = root;
        root.addSubscriber(this);

        currView = new JPanel(new BorderLayout());
        add(currView,BorderLayout.CENTER);

        fileExplorer = new JPanel();
        fileExplorer.setLayout(null);
        fileExplorer.setBackground(Color.CYAN.darker());

        setCurView(fileExplorer,true);

    }

    public void explorerMode(FileView fileView){

        fileExplorer.removeAll();

        if(fileView == null) {

            if (files == null || files.size() == 0) {
                return;
            }

            int rowNum = files.size() / colNum + 1;//calculating how many rows we need to display previews for all files

            int width = (fileExplorer.getWidth() - 40) / colNum, h = (int) (width * 1.3); // w is width of File/Doc preview and h is the height(relative)
            int x = currView.getLocation().x + 20, y = currView.getLocation().y + 20; // location where would be the first preview(upper left corner of WorkspaceView)

            fileExplorer.setPreferredSize(new Dimension(getWidth() - 40,
                    rowNum * h + 40));


            FilePreView cur; //variable to add to our layout
            int index = 0; //variable for indexing every FileView from files array


            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    if (index == files.size())
                        break;
                    cur = new FilePreView(files.get(index++));
                    cur.setBounds(x + width * j, y + h * i, width, h);
                    fileExplorer.add(cur);
                }

            }

        }

        else {

            if(fileView.getDocuments() == null || fileView.getDocuments().size() == 0){
                setCurView(fileView,true);
                return;
            }

            int rowNum = fileView.getDocuments().size() / colNum + 1;

            int width = (fileExplorer.getWidth() - 40) / colNum, h = (int) (width * 1.3);
            int x = currView.getLocation().x + 20, y = currView.getLocation().y ;
            fileExplorer.setPreferredSize(new Dimension(getWidth() - 40,
                    rowNum * h + 50));

            JLabel label = fileView.name();
            label.setBounds(currView.getLocation().x, y,fileExplorer.getWidth(),30);
            label.setOpaque(true);
            label.setBackground(new Color(0xC6F9F4));
            fileExplorer.add(label);

            DocumentPreView cur;
            int index = 0;




            for (int i = 0; i < rowNum; i++) {
                for (int j = 0; j < colNum; j++) {
                    if (index == fileView.getDocuments().size())
                        break;
                    cur = new DocumentPreView((DocumentView)fileView.getDocuments().get(index++));
                    cur.setBounds(x + width * j, y + h * i + 30, width, h);
                    fileExplorer.add(cur);
                }

            }

        }
        setCurView(fileExplorer,true);
    }

    public void setCurView(JPanel panel, boolean isExplorer){

        currView.removeAll();

        if(isExplorer){
            JScrollPane k = new JScrollPane(panel);
            k.setHorizontalScrollBar(null);
            currView.add(k,BorderLayout.CENTER);
        }
        else
            currView.add(panel,BorderLayout.CENTER);


        this.updateUI();
    }




    @Override
    public void update(Object notification) {

        Notification myNotification = (Notification) notification;


        if(myNotification.getType() == NotificationType.ADD_ACTION){
            if(files == null)
                files = new ArrayList<FileView>();
            files.add(new FileView((File)myNotification.getNotificationObject(), this));
            explorerMode(null);

        }





    }





    public void removeFile(FileView toRemove){
        files.remove(toRemove);
        explorerMode(null);
    }




}



