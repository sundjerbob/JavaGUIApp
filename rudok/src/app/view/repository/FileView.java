package app.view.repository;

import app.model.repository.Document;
import app.model.repository.File;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileView extends JPanel implements ISubscriber  {

    private static final int colNum = 4;
    private File model;

    private WorkspaceView parentView;
    private ArrayList<DocumentView> documents;

    public FileView(File model,WorkspaceView parentView){
        super(null);
        this.model = model;
        model.addSubscriber(this);
        this.parentView = parentView;
        setBackground(Color.cyan.darker());


    }



    @Override
    public void update(Object notification) {

        Notification n = (Notification) notification;
        JPanel curr = WorkspaceView.getCurrentlyOpened();

        if(n.getType() == NotificationType.DOUBLE_CLICKED){
            display();
        }

        else if(n.getType() == NotificationType.ADD_ACTION){
            if(documents == null)
                documents = new ArrayList<DocumentView>();
            documents.add(new DocumentView((Document) n.getNotificationObject(),this));
            if(curr == this)
                display();
        }
        else if(n.getType() == NotificationType.REMOVE_ACTION){
                parentView.removeFile(this);
                if(curr == this || curr == parentView.getFileExplorer())
                    parentView.setFileExplorer();
                if(curr instanceof DocumentView && ((DocumentView)curr).getParentView() == this)
                    parentView.setFileExplorer();
        }
        else if(n.getType() == NotificationType.RENAME_ACTION){
            if(curr == this)
                parentView.display(this);
            else if(curr == parentView.getFileExplorer())
                parentView.setFileExplorer();
            else if(curr instanceof DocumentView && ((DocumentView)curr).getParentView() == this)
                ((DocumentView) curr).display();
        }
    }





    public void display(){

        removeAll();

        if(documents == null || documents.size() == 0){

            parentView.display(this);

            return;
        }

        //calculating how many rows we need to display previews for all files
        int rowNum = (documents.size() % colNum == 0)? documents.size() / colNum : documents.size() / colNum + 1;

        int docPreviewWidth = (parentView.getCurrView().getWidth() - 40) / colNum;
        int docPreviewHeight = (int) (docPreviewWidth * 1.3);

        int startingLocationX = parentView.getCurrView().getLocation().x + 20; //20 is te horizontal
        int startingLocationY = parentView.getCurrView().getLocation().y - 40 ;

        setPreferredSize(new Dimension(parentView.getCurrView().getWidth() - 40,
                rowNum * docPreviewHeight + 40));

        DocumentPreview curr;

        int index;

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                index = i * colNum + j;
                if ( index == documents.size())
                    break;
                curr = new DocumentPreview(documents.get(index));
                curr.setBounds(startingLocationX + docPreviewWidth * j, startingLocationY + docPreviewHeight * i,
                        docPreviewWidth, docPreviewHeight);
                    add(curr);
            }
        }
        parentView.display(this);
    }




    public ArrayList<DocumentView> getDocuments() {
        return documents;
    }

    public File getModel() {
        return model;
    }

    public WorkspaceView getParentView() {
        return parentView;
    }

    public void removeDocument(DocumentView doc){
        documents.remove(doc);
    }

}
