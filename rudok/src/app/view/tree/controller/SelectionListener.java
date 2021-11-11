package app.view.tree.controller;

import app.controller.actions.ActionManager;
import app.model.repository.Document;
import app.model.repository.File;
import app.model.repository.Page;
import app.model.repository.Workspace;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class SelectionListener implements TreeSelectionListener {


    @Override
    public void valueChanged(TreeSelectionEvent e) {

        ActionManager manager = MainFrame.getInstance().getActionManager();
        TreeItem item = (TreeItem)e.getNewLeadSelectionPath().getLastPathComponent();

        if(item == null)
            item = MainFrame.getInstance().getITree().setRootSelected();

        else if(item.getModel() instanceof Workspace) {
            manager.getDeleteAction().setEnabled(false);
            manager.getNewAction().setEnabled(true);
            manager.getRenameAction().setEnabled(false);

        }
        else if(item.getModel() instanceof File) {
            manager.getDeleteAction().setEnabled(true);
            manager.getNewAction().setEnabled(true);
            manager.getRenameAction().setEnabled(true);
        }
        else if(item.getModel() instanceof Document) {
            manager.getDeleteAction().setEnabled(true);
            manager.getNewAction().setEnabled(true);
            manager.getRenameAction().setEnabled(true);

        }
        else if(item.getModel() instanceof Page){
            manager.getNewAction().setEnabled(false);
            manager.getDeleteAction().setEnabled(true);
            manager.getRenameAction().setEnabled(true);

        }


    }
}
