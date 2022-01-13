package app.controller.command;

import app.model.node.NodeComposite;
import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.File;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;


import java.util.ArrayList;


public class RemoveCommand implements ICommand{

    NodeComposite parent;
    NodeModel child;

    public RemoveCommand(NodeModel node) {
        child = node;
        parent = child.getParent();
    }



    @Override
    public void redo() {
        parent.removeChild(child);
        MainFrame.getInstance().getITree().setRootSelected();
        MainFrame.getInstance().getWorkspace().repaint();
    }

    @Override
    public void undo() {
        parent.addChild(child);
        if(child instanceof Document && ((Document)child).getSharedWith()!= null &&
                !((Document)child).getSharedWith().isEmpty())
        {
            ((Document) child).setSharedWith(((Document) child).getSharedWith());
        }
        if(child instanceof NodeComposite && !((NodeComposite)child).getChildren().isEmpty())
            updateView(child);
    }

    private void updateView(NodeModel node) {

        if(node instanceof NodeComposite n) {

            for( int i = 0 ; i < n.getChildren().size(); i++) {
                NodeModel k = n.getChildren().get(i);

                n.addChild(k);
                updateView(k);
            }

        }
    }
}
