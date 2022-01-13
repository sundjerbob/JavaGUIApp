package app.model.factory;


import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.Page;

public class PageFactory extends IFactory{
    @Override
    public NodeModel create() {
        return new Page(getName(pathParent, "Slide"), (Document) pathParent);
    }
}
