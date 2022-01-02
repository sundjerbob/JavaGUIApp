package app.factory;


import app.model.repository.Document;
import app.model.repository.Page;

public class PageFactory extends IFactory{
    @Override
    public void create() {
        new Page(getName(pathParent, "Slide"), (Document) pathParent);
    }
}
