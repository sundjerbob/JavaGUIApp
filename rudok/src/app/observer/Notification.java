package app.observer;


public class Notification {

    private NotificationType type;
    private Object notificationObject;

    public Notification(Object obj, NotificationType type){
        this.type = type;
        notificationObject = obj;
    }

    public NotificationType getType(){
        return type;
    }

    public Object getNotificationObject(){
        return notificationObject;
    }
}
