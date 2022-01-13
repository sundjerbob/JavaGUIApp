package app.view.repository;

import app.controller.popup.SetAuthorPopup;
import app.model.repository.Document;
import app.model.repository.File;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FileView extends JPanel implements ISubscriber  {


    private final File model;
    private final WorkspaceView parentView;
    private ArrayList<DocumentView> documents;


    public FileView(File model,WorkspaceView parentView){
        super(null);
        this.model = model;
        model.addSubscriber(this);
        this.parentView = parentView;
        setBackground(Color.cyan.darker());


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        removeAll();

        if(documents == null || documents.size() == 0){
            super.paint(g);
            return;
        }

        int docPreviewWidth = 300;
        int docPreviewHeight = 300;

        //calculating how many rows and columns we need to display previews for all documents
        int colNum = ((parentView.getCurrView().getWidth() - 40) / docPreviewWidth  == 0)?  1 : (parentView.getCurrView().getWidth() - 40) / docPreviewWidth ;
        int rowNum = (documents.size() % colNum == 0)? documents.size() / colNum : documents.size() / colNum + 1;

        int startingLocationX =  20;
        int startingLocationY = 0  ;

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

    }


    public void display(){
        parentView.display(this);
    }

    @Override
    public void update(Notification notification) {


        JPanel curr = WorkspaceView.getCurrentlyOpened();

        if(notification.getType() == NotificationType.DOUBLE_CLICK){
            display();
        }

        else if(notification.getType() == NotificationType.ADD_ACTION) {
            DocumentView newDocument = new DocumentView((Document) notification.getNotificationObject(), this);

            if(documents == null)
                documents = new ArrayList<>();

            documents.add(newDocument);


            if(curr == this)
                parentView.display(newDocument);
            else
                display();
        }

        else if(notification.getType() == NotificationType.REMOVE_ACTION){
                parentView.removeFile(this);

                if(curr == this || curr == parentView.getFileExplorer())
                    parentView.setFileExplorer();

                if(curr instanceof DocumentView && ((DocumentView)curr).getParentView() == this)
                    parentView.setFileExplorer();
        }

        else if(notification.getType() == NotificationType.RENAME_ACTION){
            if(curr == this)
                display();

            else if(curr == parentView.getFileExplorer())
                parentView.setFileExplorer();

            else if(curr instanceof DocumentView && ((DocumentView)curr).getParentView() == this)
                ((DocumentView) curr).getParentView().getParentView().display(curr);
        }
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
