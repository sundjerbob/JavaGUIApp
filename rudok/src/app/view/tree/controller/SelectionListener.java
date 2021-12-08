package app.view.tree.controller;

import app.controller.actions.ActionManager;
import app.observer.IPublisher;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.util.ArrayList;

public class SelectionListener implements TreeSelectionListener, IPublisher {


    private ArrayList<ISubscriber> subscribers;

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        ActionManager manager = MainFrame.getInstance().getActionManager();

        TreeItem item = (TreeItem)e.getNewLeadSelectionPath().getLastPathComponent();
        if(item == null)
            item = MainFrame.getInstance().getITree().setRootSelected();

        notifySubscribers(new Notification(item.getModel(),NotificationType.SELECTION_EVENT));

        /*
        else if(item.getModel() instanceof Workspace) {
            manager.getDeleteAction().setEnabled(false);
            manager.getNewAction().setEnabled(true);
            manager.getRenameAction().setEnabled(false);
            manager.getEditDocumentAction().setEnabled(false);
            manager.getSetAuthorAction().setEnabled(false);
        }
        else if(item.getModel() instanceof File) {
            manager.getDeleteAction().setEnabled(true);
            manager.getNewAction().setEnabled(true);
            manager.getRenameAction().setEnabled(true);
            manager.getEditDocumentAction().setEnabled(false);
            manager.getSetAuthorAction().setEnabled(false);
        }
        else if(item.getModel() instanceof Document) {
            manager.getDeleteAction().setEnabled(true);
            manager.getNewAction().setEnabled(true);
            manager.getRenameAction().setEnabled(true);
            manager.getEditDocumentAction().setEnabled(true);
            manager.getSetAuthorAction().setEnabled(true);

        }
        else if(item.getModel() instanceof Page){
            manager.getNewAction().setEnabled(false);
            manager.getDeleteAction().setEnabled(true);
            manager.getRenameAction().setEnabled(false);
            manager.getEditDocumentAction().setEnabled(false);
            manager.getSetAuthorAction().setEnabled(false);
        }
        */

    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscribers == null)
            subscribers = new ArrayList<ISubscriber>();
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        if(subscribers != null && subscribers.contains(subscriber))
            subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        if(subscribers == null || subscribers.isEmpty())
            return;
        for(ISubscriber curr : subscribers){
            curr.update(notification);
        }
    }
}
