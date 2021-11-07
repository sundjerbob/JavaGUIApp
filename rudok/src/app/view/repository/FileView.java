package app.view.repository;

import app.model.node.NodeModel;
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

public class FileView extends JPanel implements ISubscriber  {

    private File model;
    private WorkspaceView parentView;
    private String name;

    public FileView(File model,WorkspaceView parentView){
        this.model = model;
        this.parentView = parentView;
        name = model.getName();
        model.addSubscriber(this);
    }

    public File getModel() {
        return model;
    }



    @Override
    public void update(Object notification) {

        Notification myNotification = (Notification) notification;

        if(myNotification.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeFile(this);

        }
        else if(myNotification.getType() == NotificationType.RENAME_ACTION){
            name = model.getName();
            parentView.explorerMode();
        }
    }




}
