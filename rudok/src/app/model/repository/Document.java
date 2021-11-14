package app.model.repository;


import app.model.node.NodeComposit;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Document extends NodeComposit {

    private String author;
    private Image theme;

    public Document(String name, File parent) {
        super(name, parent);
    }

    public Image getTheme() {
        return theme;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
        notifySubscribers(new Notification(this, NotificationType.AUTHOR_SET));
    }

    public void setTheme(int theme){

        String fileName;
        switch (theme){
            case 1 : fileName = "../../controller/popup/images/spring.jpg"; break;
            case 2 : fileName = "../../controller/popup/images/summer.png"; break;
            case 3 : fileName = "../../controller/popup/images/fall.jpg"; break;
            case 4 : fileName = "../../controller/popup/images/winter.png"; break;
            default: fileName = null;
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
            return ;
        }

    }

}
