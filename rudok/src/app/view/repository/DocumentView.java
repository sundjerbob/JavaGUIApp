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
    private JLabel name;
    private FileView parentView;
    private ArrayList<PageView> pages;

    public DocumentView(Document model,FileView parentView){
        super(new BorderLayout());
        this.model = model;
        model.addSubscriber(this);

        this.parentView = parentView;
        name = new JLabel(model.getName());
        name.setPreferredSize(new Dimension((int)parentView.getPreferredSize().getWidth(),30));

        add(name,BorderLayout.NORTH);

    }


    @Override
    public void update(Object notification) {

        Notification myNotification = (Notification) notification;

        if(myNotification.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeDocument(this);

        }
        else if(myNotification.getType() == NotificationType.RENAME_ACTION){
            name.setText(model.getName());
            parentView.getParentView().explorerMode(parentView);
        }
    }

    public Document getModel() {
        return model;
    }
}
