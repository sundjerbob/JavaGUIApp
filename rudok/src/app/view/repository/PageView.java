package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.Page;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;

public class PageView extends JPanel implements ISubscriber {

    private Page model;
    private JLabel label;
    private DocumentView parent;

    public PageView(Page model,DocumentView parent){
        super(new BorderLayout());
        this.model = model;
        this.parent = parent;

        setBackground(Color.red);
        label = new JLabel(model.getName(),SwingConstants.CENTER);

        add(label,BorderLayout.SOUTH);

    }


    @Override
    public void update(Object notification) {

    }

    public NodeModel getModel() {
        return model;
    }


    public DocumentView getParentView() {
        return parent;
    }
}
