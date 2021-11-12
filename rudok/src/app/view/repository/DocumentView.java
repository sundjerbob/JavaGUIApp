package app.view.repository;

import app.model.repository.Document;
import app.model.repository.Page;
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
    private PageView currentPage;
    private JLabel name;
    private JPanel center;

    public DocumentView(Document model,FileView parentView){
        super(new BorderLayout());
        this.model = model;
        this.parentView = parentView;
        model.addSubscriber(this);
        setBackground(Color.cyan.darker());
        name = new JLabel( "     " + model.getName());
        name.setPreferredSize(new Dimension(0,50));
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setVerticalAlignment(SwingConstants.CENTER);
        add(name,BorderLayout.NORTH);
        center = new JPanel(new BorderLayout());
        add(center,BorderLayout.CENTER);


    }


    @Override
    public void update(Object notification) {

        Notification n = (Notification) notification;
        JPanel curr = WorkspaceView.getCurrentlyOpened();

        if(n.getType() == NotificationType.ADD_ACTION){
            if(pages == null)
                pages = new ArrayList<PageView>();
           PageView newPage = new PageView((Page) n.getNotificationObject(),this);
           pages.add(newPage);
           setCurrentPage(newPage);
           if(curr == this)
               display();
        }
        else if(n.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeDocument(this);
            if( curr == this ||
                    WorkspaceView.getCurrentlyOpened() == parentView)
                parentView.display();
        }
        else if(n.getType() == NotificationType.RENAME_ACTION){
            if( curr == parentView)
                parentView.display();
            else if( curr == this ){
                name.setText("     " + model.getName());
                display();
            }

        }
    }

    public void display(){
        parentView.getParentView().display(this);
    }

    public Document getModel() {
        return model;
    }

    public FileView getParentView() {
        return parentView;
    }

    public PageView getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(PageView currentPage) {
        center.removeAll();
        center.add(currentPage,BorderLayout.CENTER);
        this.currentPage = currentPage;
        updateUI();
    }
}
