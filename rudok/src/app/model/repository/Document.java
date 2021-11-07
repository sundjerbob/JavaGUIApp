package app.model.repository;


import app.model.node.NodeComposit;
import app.observer.ISubscriber;

public class Document extends NodeComposit {

    public Document(String name, File parent) {
        super(name, parent);
    }


}
