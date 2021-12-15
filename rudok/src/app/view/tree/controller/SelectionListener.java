package app.view.tree.controller;


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

        TreeItem item = (TreeItem)e.getNewLeadSelectionPath().getLastPathComponent();
        if(item == null)
            item = MainFrame.getInstance().getITree().setRootSelected();

        notifySubscribers(new Notification(item.getModel(),NotificationType.SELECTION_EVENT));

    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscribers == null)
            subscribers = new ArrayList<>();
        subscribers.add(subscriber);
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
