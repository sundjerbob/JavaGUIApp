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
    private DocumentView parentView;

    public PageView(Page model,DocumentView parent){
        super(new BorderLayout());
        this.model = model;
        model.addSubscriber(this);
        parentView = parent;

        setBackground(Color.white);
        label = new JLabel(model.getName(),SwingConstants.CENTER);

        add(label,BorderLayout.SOUTH);

    }


    @Override
    public void update(Object notification) {
        Notification n = (Notification) notification;

        if(n.getType() == NotificationType.DOUBLE_CLICKED){

            parentView.setCurrentPage(this);
            parentView.display();
        }

        else if(n.getType() == NotificationType.RENAME_ACTION){
            label.setText(model.getName());
            if(parentView.getCurrentPage() == this){
                parentView.display();
            }
        }
        else if(n.getType() == NotificationType.REMOVE_ACTION){
            int i = parentView.getIndexOfPage(this);
            if(parentView.getCurrentPage() == this ){
                if(i == 0) {
                    if(parentView.getPages().size() == 1) {
                        parentView.setCurrentPage(null);
                    }
                    else if(parentView.getPages().size() > 1){
                        parentView.setCurrentPage(parentView.getPages().get(i+1));
                    }
                }
                    else
                        parentView.setCurrentPage(parentView.getPages().get(i-1));
            }
            parentView.removePage(this);
        }



    }


    public NodeModel getModel() {
        return model;
    }


    public DocumentView getParentView() {
        return parentView;
    }
}
