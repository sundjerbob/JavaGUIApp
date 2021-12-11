package app.view.tree.model;

import app.model.node.*;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Iterator;

public class TreeItem extends DefaultMutableTreeNode implements ISubscriber {

    private NodeModel model;


    public TreeItem(NodeModel model){
        this.model = model;
        model.addSubscriber(this);
    }



    @Override
    public void update(Notification notification) {
    if(notification.getType() == NotificationType.ADD_ACTION){
        add(new TreeItem(notification.getNotificationObject()));
        MainFrame.getInstance().getITree().getTreeView().updateUI();
    }
    else if(notification.getType() == NotificationType.REMOVE_ACTION){
        ((MutableTreeNode)getParent()).remove(this);
        MainFrame.getInstance().getITree().getTreeView().updateUI();
    }
    else if(notification.getType() == NotificationType.RENAME_ACTION){
        MainFrame.getInstance().getITree().getTreeView().updateUI();
    }

    }

    @Override
    public boolean isLeaf(){
        if(model instanceof NodeComposite )
            return false;
        return true;
    }

    @Override
    public boolean getAllowsChildren() {
        if(model instanceof NodeComposite)
            return true;
        return false;
    }



    @Override
    public String toString(){
        return model.getName();
    }




    public NodeModel getModel() {
        return model;
    }



    public void setModel(NodeModel model) {
        this.model = model;
    }



}
