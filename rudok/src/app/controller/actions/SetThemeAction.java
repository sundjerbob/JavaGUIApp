package app.controller.actions;

import app.controller.popup.SetAuthorPopup;
import app.controller.popup.SetThemePopup;
import app.model.repository.Document;
import app.observer.Notification;
import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SetThemeAction extends MyAbstractAction {



    public SetThemeAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/pictureSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/pictureLarge.png"));
        putValue(NAME,"Set theme");
        putValue(SHORT_DESCRIPTION,"Set theme for selected document.");
        setEnabled(false);
        MainFrame.getInstance().getITree().getTreeView().getSelectionListener().addSubscriber(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        new SetThemePopup();

    }

    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof Document)
            setEnabled(true);
        else
            setEnabled(false);
    }
}
