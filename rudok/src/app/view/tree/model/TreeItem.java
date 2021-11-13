package app.view.tree.model;

import app.model.node.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;
import java.util.Iterator;

public class TreeItem extends DefaultMutableTreeNode {

    private String name;
    private NodeModel model;


    public TreeItem(NodeModel model){
        this.model = model;
        name = model.getName();
    }



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
    public String toString(){
        return name;
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
