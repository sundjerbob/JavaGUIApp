package app.factory;


import app.model.repository.File;
import app.model.repository.Workspace;

public class FileFactory extends IFactory{
    @Override
    public void create() {
        new File(getName(pathParent,"File"), (Workspace) pathParent);
    }
}
