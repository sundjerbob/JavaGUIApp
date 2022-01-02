package app.view.repository;

import app.model.repository.File;
import app.model.repository.Workspace;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.Label;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;

public class WorkspaceView extends JPanel implements ISubscriber {

     //layout will have 5 previews per row
    private static JPanel currentlyOpened;

    private final Workspace model;
    private final JPanel currView ;
    private ArrayList<FileView> files;
    private final JPanel fileExplorer;
    private final Label label;



    public WorkspaceView(Workspace root){
        super(new BorderLayout());

        model = root;
        root.addSubscriber(this);

        label = new Label(this);
        add(label,BorderLayout.NORTH);

        currView = new JPanel(new BorderLayout()); //all content that is opened will be in this panel
        add(currView,BorderLayout.CENTER);

        fileExplorer = new JPanel(null){

            @Override
            public void paint(Graphics g) {
                super.paint(g);

                fileExplorer.removeAll();

                if (files != null && !files.isEmpty()){
                    int filePreviewWith = 200;
                    int filePreviewHeight = (int) (filePreviewWith * 1.3);

                    int colNum =  ((currView.getWidth() - 40) / filePreviewWith == 0)? 1 : (currView.getWidth() - 40) / filePreviewWith ;
                    int rowNum = (files.size() % colNum == 0)? files.size() / colNum : files.size() / colNum + 1;

                    int x = 20;
                    int y = 0;

                    fileExplorer.setPreferredSize(new Dimension(currView.getWidth() - 40, rowNum * filePreviewHeight));

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
                }

            }
        };

        fileExplorer.setBackground(Color.CYAN.darker());
        display(fileExplorer);
    }

    public void display(JPanel setView){ //this method is used by FileView and DocumentView to display them self's on workspace

        currentlyOpened = setView;//changed

        currView.removeAll();

        label.setCurrPath();            //refreshing the upper label every time the displaying content is

        if(setView == fileExplorer || setView instanceof FileView ) {//if workspace view or some file is open we need scroll
            if(setView == fileExplorer)
                MainFrame.getInstance().getITree().setRootSelected();
            else {
                TreeItem item = MainFrame.getInstance().getITree().findItemByModel(((FileView)setView).getModel());
                MainFrame.getInstance().getITree().getTreeView().setSelectionPath(
                        new TreePath(item.getPath()));
            }
            JScrollPane pane = new JScrollPane(setView);
            pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            currView.add(pane,BorderLayout.CENTER);
        }
        else
        {
            TreeItem item = MainFrame.getInstance().getITree().findItemByModel(((DocumentView)setView).getModel());
            MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));
            currView.add(setView,BorderLayout.CENTER);
        }
        updateUI();
    }


    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof File &&
                notification.getType() == NotificationType.ADD_ACTION){
            if(files == null){
                files = new ArrayList<>();
            }
            files.add(new FileView((File) notification.getNotificationObject(),this));
            setFileExplorer();
            updateUI();
        }
    }


    public void setFileExplorer(){              //this method is used by WorkspaceView to display File Explorer view
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
}



