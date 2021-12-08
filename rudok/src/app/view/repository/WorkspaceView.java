package app.view.repository;

import app.model.repository.File;
import app.model.repository.Workspace;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.Label;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorkspaceView extends JPanel implements ISubscriber {

    private static final int colNum = 5; //layout will have 5 previews per row
    private static JPanel currentlyOpened;




    private Workspace model;
    private  JPanel currView  ;
    private ArrayList<FileView> files;
    private JPanel fileExplorer;
    private app.view.gui.Label label;



    public WorkspaceView(Workspace root){
        super(new BorderLayout());

        model = root;
        root.addSubscriber(this);

        label = new Label(this);
        add(label,BorderLayout.NORTH);

        currView = new JPanel(new BorderLayout()); //all content that is opened will be in this panel
        add(currView,BorderLayout.CENTER);
        currView.setBackground(Color.pink);

        fileExplorer = new JPanel(); //this panel is used for generating a matrix-like view of Previews
        fileExplorer.setLayout(null);
        fileExplorer.setBackground(Color.CYAN.darker());

        display(fileExplorer);


    }
    public void display(JPanel setView){        //this method is used by FileView and DocumentView to display them
        currView.removeAll();                   //on workspace

        JScrollPane scrollPane = new JScrollPane(setView);
        scrollPane.setHorizontalScrollBar(null);
        currView.add(scrollPane);

        setCurrentlyOpened(setView);
        label.setCurrPath();            //refreshing the upper label every time the displaying content is
                                            //changed
        updateUI();
    }


    @Override
    public void update(Notification notification) {

        if(notification.getNotificationObject() instanceof File &&
                notification.getType() == NotificationType.ADD_ACTION){
            if(files == null){
                files = new ArrayList<FileView>();
            }
            files.add(new FileView((File) notification.getNotificationObject(),this));
            if(currentlyOpened == fileExplorer)
                setFileExplorer();
        }

    }

    public void setFileExplorer(){              //this method is used by WorkspaceView to display File Explorer view

        fileExplorer.removeAll();

        if (files == null || files.size() == 0) {
                display(fileExplorer);
                return;
            }

        //calculating how many rows we need to display previews for all files
        int rowNum = (files.size() % colNum == 0)? files.size() / colNum : files.size() / colNum + 1;

        int filePreviewWith = (getWidth() - 40) / colNum, filePreviewHeight = (int) (filePreviewWith * 1.3); // w is filePreviewWith of File/Doc preview and h is the filePreviewHeight(relative)
        int x = currView.getLocation().x + 20, y = currView.getLocation().y - 40 ; // location where would be the first preview(upper left corner of WorkspaceView)

        fileExplorer.setPreferredSize(new Dimension(getWidth() - 40, rowNum * filePreviewHeight));

        FilePreview curr;
        int index ;


        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                index = i * colNum + j;
                if (index == files.size())
                    break;

                curr = new FilePreview(files.get(index));
                curr.setBounds(x + filePreviewWith * j, y + filePreviewHeight * i, filePreviewWith, filePreviewHeight);
                fileExplorer.add(curr);
            }
        }
        display(fileExplorer);
    }


    public void removeFile(FileView me){
        files.remove(me);
    }

    public JPanel getCurrView() {
        return currView;
    }

    public JPanel getFileExplorer() {
        return fileExplorer;
    }

    public static JPanel getCurrentlyOpened() {
        return currentlyOpened;
    }

    private static void setCurrentlyOpened(JPanel currentlyOpened) {
        WorkspaceView.currentlyOpened = currentlyOpened;
    }

}



