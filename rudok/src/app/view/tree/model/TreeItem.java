package app.view.tree.model;

import app.model.node.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;

public class TreeItem extends DefaultMutableTreeNode {

    private String name;
    private NodeModel model;


    @Override
    public boolean isLeaf(){
        if(model instanceof NodeComposit )
            return false;
        return true;
    }

    @Override
    public boolean getAllowsChildren() {
        if(model instanceof NodeComposit)
            return true;
        return false;
    }

    @Override
    public Enumeration children(){
        if(model instanceof NodeComposit)
            return (Enumeration)((NodeComposit)model).getChildren();
        return null;
    }

    @Override
    public String toString(){
        return name;
    }

    public TreeItem(NodeModel model){
        this.model = model;
        name = model.getName();
    }

    public String getName() {
        return name;
    }

    public NodeModel getModel() {
        return model;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(NodeModel model) {
        this.model = model;
    }
}
