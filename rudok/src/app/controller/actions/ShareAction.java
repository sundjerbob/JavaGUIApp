package app.controller.actions;

import app.controller.actions.MyAbstractAction;
import app.controller.popup.SharePopup;
import app.model.repository.Document;

import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import java.awt.event.ActionEvent;

public class ShareAction extends MyAbstractAction {

    public ShareAction() {

        MainFrame.getInstance().getITree().getSelectionListener().addSubscriber(this);
        putValue(SMALL_ICON, loadIcon("images/shareDoc.png"));
        putValue(SHORT_DESCRIPTION,"Share this presentation.");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem d = MainFrame.getInstance().getITree().getSelectedTreeItem();

        if( d.getModel() instanceof Document )
            new SharePopup(d);
    }

    @Override
    public void update(Notification notification) {
        if(notification.getType() == NotificationType.SELECTION_EVENT){
            if(notification.getNotificationObject() instanceof Document)
                setEnabled(true);
            else
                setEnabled(false);
        }
    }
}
