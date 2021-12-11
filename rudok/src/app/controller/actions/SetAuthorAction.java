package app.controller.actions;

import app.controller.popup.SetAuthorPopup;
import app.model.repository.Document;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.view.gui.MainFrame;


import java.awt.event.ActionEvent;


public class SetAuthorAction extends MyAbstractAction  {

    public SetAuthorAction(){

        putValue(SMALL_ICON, loadIcon("images/authorSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/authorLarge.png"));
        putValue(NAME,"Set author");
        putValue(SHORT_DESCRIPTION,"Set author.");
        setEnabled(false);
        MainFrame.getInstance().getITree().getTreeView().getSelectionListener().addSubscriber(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new SetAuthorPopup(null);
    }

    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof Document)
            setEnabled(true);
        else
            setEnabled(false);
    }
}
