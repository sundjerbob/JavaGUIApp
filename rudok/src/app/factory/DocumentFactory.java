package app.factory;


import app.model.repository.Document;
import app.model.repository.File;

public class DocumentFactory extends IFactory{


    @Override
    public void create() {
        new Document(getName(pathParent,"Presentation"), (File) pathParent);
    }
}
