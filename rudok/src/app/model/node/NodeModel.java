package app.model.node;

import app.observer.IPublisher;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import java.util.ArrayList;

public abstract class NodeModel implements IPublisher {

    private final NodeComposite parent;
    private final ArrayList<ISubscriber> subscribers;
    private  String name;

    public NodeModel (String name,NodeComposite parent){
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();

        if(parent != null)
            parent.addChild(this);
    }

        //////////////////

    public void setName(String name) {
        this.name = name;
        notifySubscribers(new Notification(this, NotificationType.RENAME_ACTION));
    }

        //////////////////

    @Override
    public void addSubscriber(ISubscriber subscriber) {
    if(subscriber != null && !subscribers.contains(subscriber))
        subscribers.add(subscriber);

    }

    @Override
    public void notifySubscribers (Notification notification) {
        if(!subscribers.isEmpty())
            for(ISubscriber curr : subscribers){
                curr.update(notification);
        }
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof NodeModel)
            return ((NodeModel)o).getName().equals(this.name);
        return false;
    }

    public String getName(){
        return name;
    }

    public NodeComposite getParent(){
        return parent;
    }

    @Override
    public String toString(){
        return name;
    }


}
