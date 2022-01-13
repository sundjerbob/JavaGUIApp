package app.controller.command;

import app.model.node.NodeComposite;



public class RenameCommand implements ICommand{

    private String newName;
    private NodeComposite node;
    private String oldName;

    public RenameCommand(String name,NodeComposite node) {
        newName = name;
        oldName = node.getName();
        this.node = node;

    }

    @Override
    public void redo() {
        System.out.println(oldName);
        node.setName(newName);


    }
    @Override
    public void undo() {
        node.setName(oldName);

    }


}
