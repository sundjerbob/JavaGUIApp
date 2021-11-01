package app.model.node;

import java.util.ArrayList;

public abstract class NodeComposit extends NodeModel {


    private ArrayList<NodeModel> children = null;



    public NodeComposit(String name,NodeComposit parent) {
        super(name, parent);
        children = new ArrayList<NodeModel>();
    }


    public NodeComposit(String name,NodeComposit parent,ArrayList<NodeModel> children){
        super(name,parent);
        this.children = children;
    }


    public ArrayList<NodeModel> getChildren() {
        return children;
    }


    public void addChild(NodeModel newNode){
        if(children.contains(newNode))
            return;
        children.add(newNode);
    }


    public void removeChild(NodeModel unwantedChild){
        if(children.contains(unwantedChild))
            children.remove(unwantedChild);
        return;
    }


    public NodeModel getChildByName(String name){
        for(NodeModel cur : children){
            if(cur.getName().equals(name))
                return cur;
        }
        System.out.println("there is no child with that name here.");
        return null;
    }


}