package app.model.factory;


import app.model.node.NodeModel;
import app.model.repository.File;
import app.model.repository.Workspace;

public class FileFactory extends IFactory{
    @Override
    public NodeModel create() {
       return new File(getName(pathParent,"File"), (Workspace) pathParent);
    }
}
