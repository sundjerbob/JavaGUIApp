package app.model.node;

public abstract class NodeModel {

    private NodeComposit parent;
    private String name;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(NodeComposit parent) {
        this.parent = parent;
    }
}
