package app.view.tree.model;

import app.model.node.*;
import app.model.repository.Document;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.Iterator;

public class TreeItem extends DefaultMutableTreeNode implements ISubscriber {

    private NodeModel model;
    private boolean isCopy;


    public TreeItem(NodeModel model, boolean isCopy){
        this.model = model;
        this.isCopy = isCopy;
        model.addSubscriber(this);

    }



    @Override
    public void update(Notification notification) {
        if(notification.getType() == NotificationType.ADD_ACTION) {

            add(new TreeItem((NodeModel) notification.getNotificationObject(),false));
            MainFrame.getInstance().getITree().getTreeView().updateUI();
        }
        else if(notification.getType() == NotificationType.REMOVE_ACTION){
            ((MutableTreeNode)getParent()).remove(this);
            MainFrame.getInstance().getITree().getTreeView().updateUI();

        }
        else if(notification.getType() == NotificationType.RENAME_ACTION){
            MainFrame.getInstance().getITree().getTreeView().updateUI();
        }
        else if(notification.getType() == NotificationType.DOCUMENT_SHARED) {
            if(hasChild((Document) notification.getNotificationObject()) == null) {
                add(new TreeItem((NodeModel) notification.getNotificationObject(),true));
                MainFrame.getInstance().getITree().getTreeView().updateUI();
            }
        }
        else if(notification.getType() == NotificationType.DOCUMENT_UNSHARED) {
            MutableTreeNode node = hasChild((Document) notification.getNotificationObject());
            if( node != null){
                remove(node);
                MainFrame.getInstance().getITree().getTreeView().updateUI();
            }
        }


    }

    private TreeItem hasChild(Document node) {

        if(children != null) {
            Iterator iterator = children.iterator();
            TreeItem curr;
            while (iterator.hasNext()) {
                curr = (TreeItem) iterator.next();
                if(curr.getModel() == node)
                    return curr;
            }
        }
        return null;
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
        return (isCopy)? model.toString() + '*' : model.toString();
    }


    public NodeModel getModel() {
        return model;
    }


    public void setModel(NodeModel model) {
        this.model = model;
    }


}
