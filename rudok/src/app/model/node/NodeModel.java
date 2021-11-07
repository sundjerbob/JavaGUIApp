package app.model.node;

import app.observer.IPublisher;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import java.util.ArrayList;

public abstract class NodeModel implements IPublisher {

    private NodeComposit parent;
    private String name;
    private ArrayList<ISubscriber> subscribers;

    public NodeModel (String name,NodeComposit parent){
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String toString(){
        return name;
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
    public NodeComposit getParent(){
        return parent;
    }
        //////////////////


    public void setName(String name) {
        this.name = name;
        notifySubscribers(new Notification(this, NotificationType.RENAME_ACTION));
    }


        //////////////////
    public void setParent(NodeComposit parent) {
        this.parent = parent;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {

        if(subscriber == null)
            return;
        if(subscribers == null)
            subscribers = new ArrayList<ISubscriber>();
        if(subscribers.contains(subscriber))
            return;
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {

        if(subscriber == null || subscribers == null || !subscribers.contains(subscriber))
            return;
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || subscribers == null || subscribers.isEmpty())
            return;

        for(ISubscriber cur : subscribers){
            cur.update(notification);
        }
    }
}
