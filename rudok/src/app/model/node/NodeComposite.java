package app.model.node;

import app.observer.Notification;
import app.observer.NotificationType;

import java.util.ArrayList;

public abstract class NodeComposite extends NodeModel {


    private ArrayList<NodeModel> children = null;



    public NodeComposite(String name,NodeComposite parent) {
        super(name, parent);
        children = new ArrayList<NodeModel>();
    }


    public NodeComposite(String name,NodeComposite parent,ArrayList<NodeModel> children){
        super(name,parent);
        this.children = children;
    }


    public ArrayList<NodeModel> getChildren() {
        return children;
    }

    //observer
    public void addChild(NodeModel newNode){
        if(children.contains(newNode))
            return;
        children.add(newNode);
        notifySubscribers(new Notification(newNode, NotificationType.ADD_ACTION));
    }


    public void removeChild(NodeModel unwantedChild){
        if(children.contains(unwantedChild)) {
            children.remove(unwantedChild);
            unwantedChild.notifySubscribers(new Notification(unwantedChild,NotificationType.REMOVE_ACTION));
        }
        return;
    }


    public NodeModel getChildByName(String name){

        for(NodeModel cur : children){
            if(cur.getName().equals(name))
                return cur;
        }

        return null;
    }



}
