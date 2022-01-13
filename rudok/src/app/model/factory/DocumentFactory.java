package app.model.factory;


import app.controller.popup.SetAuthorPopup;
import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.File;
import app.view.repository.DocumentView;

public class DocumentFactory extends IFactory{


    @Override
    public NodeModel create() {
        NodeModel newNode = new Document(getName(pathParent,"Presentation"), (File) pathParent);
        return newNode;
    }
}
