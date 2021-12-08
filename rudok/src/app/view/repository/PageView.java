package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.Document;
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
    private Image image;
    private JPanel bgPanel;

    public PageView(Page model,DocumentView parent) {
        super(new BorderLayout());
        this.model = model;
        model.addSubscriber(this);
        parentView = parent;


        setBackground(Color.white);
        label = new JLabel(model.getName(), SwingConstants.CENTER);
        label.setOpaque(true);
        add(label, BorderLayout.SOUTH);

        bgPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null)
                    g.drawImage(image, 0, 0, null);
            }
        };
        setBackgroundTheme();
        bgPanel.add(label, BorderLayout.SOUTH);
        add(bgPanel, BorderLayout.CENTER);

    }


    @Override
    public void update(Notification notification) {
        Notification n = notification;

        if(n.getType() == NotificationType.DOUBLE_CLICK){

            parentView.setCurrentPage(this);
            parentView.display();
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

    public void setBackgroundTheme(){
        image = ((Document) getModel().getParent()).getTheme();
        if (image == null) {
            bgPanel.setOpaque(true);
        } else
            bgPanel.setOpaque(false);

        bgPanel.updateUI();

    }

    public NodeModel getModel() {
        return model;
    }


    public DocumentView getParentView() {
        return parentView;
    }
}
