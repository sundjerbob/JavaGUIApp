package app.observer;

import app.model.node.NodeModel;

public class Notification {

    private NotificationType type;
    private NodeModel notificationObject;

    public Notification(NodeModel obj, NotificationType type){
        this.type = type;
        notificationObject = obj;
    }

    public NotificationType getType(){
        return type;
    }

    public NodeModel getNotificationObject(){
        return notificationObject;
    }
}
