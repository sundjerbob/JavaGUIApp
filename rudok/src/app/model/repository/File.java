package app.model.repository;

import app.model.node.*;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import java.util.ArrayList;

public class File extends NodeComposite {

    public File(String name, Workspace parent) {
        super(name, parent);

    }

    public void docSharing(Document d) {
        if(d.getSharedWith().contains(this) && !getChildren().contains(d))
        {
            System.out.println("PRAVIM KOPIJU KOD SEBE");
            getChildren().add(d);
            notifySubscribers(new Notification(d, NotificationType.DOCUMENT_SHARED));
        }

        else if(!d.getSharedWith().contains(this) && getChildren().contains(d)){
            getChildren().remove(d);
            System.out.println("Brisem kopiju");
            notifySubscribers(new Notification(d, NotificationType.DOCUMENT_UNSHARED));
        }

    }
}
