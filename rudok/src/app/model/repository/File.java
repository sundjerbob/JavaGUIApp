package app.model.repository;

import app.model.node.*;
import app.observer.ISubscriber;

import java.util.ArrayList;

public class File extends NodeComposite {

    private ArrayList<Slot> slots;

    public File(String name, Workspace parent) {
        super(name, parent);
        slots = new ArrayList<>();
    }



}
