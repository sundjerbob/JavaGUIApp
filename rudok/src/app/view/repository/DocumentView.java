package app.view.repository;

import app.model.repository.Document;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DocumentView extends JPanel implements ISubscriber {

    private Document model;
    private FileView parentView;
    private ArrayList<PageView> pages;

    public DocumentView(Document model,FileView parentView){
        super(new BorderLayout());
        this.model = model;
        model.addSubscriber(this);
        JLabel label  = new JLabel(model.getName());
        add(label,BorderLayout.WEST);
        this.parentView = parentView;


    }


    @Override
    public void update(Object notification) {

        Notification n = (Notification) notification;
        if(n.getType() == NotificationType.ADD_ACTION){

        }
        else if(n.getType() == NotificationType.REMOVE_ACTION){

        }
    }

    public Document getModel() {
        return model;
    }

    public FileView getParentView() {
        return parentView;
    }
}
