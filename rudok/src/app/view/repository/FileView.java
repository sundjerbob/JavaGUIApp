package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.File;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FileView extends JPanel implements ISubscriber  {

    private File model;
    private JLabel name;
    private WorkspaceView parentView;
    private ArrayList<DocumentView> documents;

    public FileView(File model,WorkspaceView parentView){
        super(new BorderLayout());
        this.model = model;
        model.addSubscriber(this);
        this.parentView = parentView;
        setBackground(Color.CYAN);

        name = new JLabel(model.getName(),SwingConstants.CENTER);
        add(name,BorderLayout.NORTH);
    }



    @Override
    public void update(Object notification) {

        Notification myNotification = (Notification) notification;

        if(myNotification.getType() == NotificationType.ADD_ACTION){
            if(documents == null)
                documents = new ArrayList<DocumentView>();
                documents.add(new DocumentView((Document) myNotification.getNotificationObject(),this));
                parentView.explorerMode(this);
        }

        else if(myNotification.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeFile(this);

        }
        else if(myNotification.getType() == NotificationType.RENAME_ACTION){
            name.setText(model.getName());
            parentView.explorerMode(null);
        }
    }

    public void removeDocument(DocumentView toRemove){
        documents.remove(toRemove);
        parentView.explorerMode(this);
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


    public JLabel name() {
        return name;
    }
}
