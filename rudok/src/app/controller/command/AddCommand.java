package app.controller.command;

import app.model.factory.Factory;
import app.model.node.NodeComposite;
import app.model.node.NodeModel;
import app.view.gui.MainFrame;

public class AddCommand implements ICommand {

    private NodeComposite parent;
    private NodeModel child;

    public  AddCommand(NodeComposite node) {
        parent = node;
    }

    @Override
    public void redo() {
        child = Factory.getMe().getFactory(parent).create();
        parent.addChild(child);
        MainFrame.getInstance().getWorkspace().repaint();
    }

    @Override
    public void undo() {
        parent.removeChild(child);
        MainFrame.getInstance().getWorkspace().repaint();
    }




}
