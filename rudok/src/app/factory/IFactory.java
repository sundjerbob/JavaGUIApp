package app.factory;

import app.model.node.NodeComposite;



public abstract class IFactory {

    protected NodeComposite pathParent;

    public abstract  void create();

    public void setPathParent(NodeComposite pathParent) {
        this.pathParent = pathParent;
    }

    protected String getName(NodeComposite model, String name){

        int num = 1;
        while(model.getChildByName(name + " " + num) != null) {
            ++num;
        }
        return name + " " + num;
    }

}
