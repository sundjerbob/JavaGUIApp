package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.Page;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;

public class PageView extends JPanel implements ISubscriber {

    private NodeModel model;
    private JLabel label;
    private DocumentView parent;

    public PageView(NodeModel model,DocumentView parent){
        super(new BorderLayout());
        this.model = model;
        this.parent = parent;

        label = new JLabel(model.getName(),SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(50,100));
        add(label,BorderLayout.CENTER);

    }


    @Override
    public void update(Object notification) {
        Notification myNotification = (Notification) notification;
        if(myNotification.getType() == NotificationType.RENAME_ACTION){
            label.setText(model.getName());
        }
        else if(myNotification.getType() == NotificationType.REMOVE_ACTION){

        }
    }

    public NodeModel getModel() {
        return model;
    }


    public DocumentView getParentView() {
        return parent;
    }
}
