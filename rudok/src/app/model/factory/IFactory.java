package app.model.factory;

import app.model.node.NodeComposite;
import app.model.node.NodeModel;


public abstract class IFactory {

    protected NodeComposite pathParent;

    public abstract NodeModel create();

    public void setPathParent(NodeComposite pathParent) {
        this.pathParent = pathParent;
    }

    protected String getName(NodeComposite model, String name) {

        int num = 1;
        while(model.getChildByName(name + " " + num) != null) {
            ++num;
        }
        return name + " " + num;
    }

}
