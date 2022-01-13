package app.model.repository;


import app.model.node.NodeComposite;
import app.model.node.NodeModel;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Document extends NodeComposite {

    private String author;
    private Image theme;
    private List sharedWith;

    public Document(String name, File parent) {
        super(name, parent);
    }



    public void setAuthor(String author){
        this.author = author;
        notifySubscribers(new Notification(this, NotificationType.AUTHOR_SET));
    }

    public void setTheme(int theme){

        String fileName;
        switch (theme){
            case 1 -> fileName = "../../controller/popup/images/spring.jpg";
            case 2 -> fileName = "../../controller/popup/images/summer.png";
            case 3 -> fileName = "../../controller/popup/images/fall.jpg";
            case 4 -> fileName = "../../controller/popup/images/winter.png";
            default -> fileName = null;
        }

        if(fileName == null){
            this.theme = null;
            notifySubscribers(new Notification(this,NotificationType.THEME_SET));
            return;
        }

        URL imageURL = getClass().getResource(fileName);


        if(imageURL != null){
            this.theme = new ImageIcon(imageURL).getImage();
            notifySubscribers(new Notification(this,NotificationType.THEME_SET));
        }
        else {
            System.out.println("class: Document setTheme failed.");
        }

    }

    public Image getTheme() {
        return theme;
    }

    public String getAuthor() {
        return author;
    }

    public void setSharedWith(List list) {
        sharedWith = list;
        ArrayList<NodeModel> files = MainFrame.getInstance().getWorkspace().getModel().getChildren();
        for(NodeModel curr : files) {
            if(curr != getParent() ) {
                if(sharedWith.contains(curr))
                    curr.notifySubscribers(new Notification(this, NotificationType.DOCUMENT_SHARED));
                else
                    curr.notifySubscribers(new Notification(this, NotificationType.DOCUMENT_UNSHARED));
            }
        }
    }

    public ArrayList<File> getSharedWith() {

        return (ArrayList<File>) sharedWith;
    }
}
