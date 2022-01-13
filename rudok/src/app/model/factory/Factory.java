package app.model.factory;

import app.model.node.NodeComposite;
import app.model.repository.File;
import app.model.repository.Workspace;


public class Factory {

    private static Factory me;
    private final IFactory fileFactory;
    private final IFactory documentFactory;
    private final IFactory pageFactory;

    private Factory() {
        fileFactory = new FileFactory();
        documentFactory = new DocumentFactory();
        pageFactory = new PageFactory();
    }

    public static Factory getMe() {
        if(me == null)
            me = new Factory();
        return me;
    }

    public IFactory getFactory(NodeComposite node) {
        if(node instanceof Workspace){
            fileFactory.setPathParent(node);
            return fileFactory;
        }
        else if(node instanceof File){
            documentFactory.setPathParent(node);
            return documentFactory;
        }
        else{
            pageFactory.setPathParent(node);
            return pageFactory;
        }
    }

}
