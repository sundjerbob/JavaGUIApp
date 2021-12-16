package app.controller.actions;

import app.controller.popup.RenamePopup;
import app.model.repository.Page;
import app.model.repository.Workspace;
import app.observer.Notification;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends  MyAbstractAction  {

    public RenameAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK,true));
        putValue(SMALL_ICON, loadIcon("images/editingSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/editing.png"));
        putValue(NAME,"Rename");
        putValue(SHORT_DESCRIPTION,"Rename");
        setEnabled(false);
        MainFrame.getInstance().getITree().getTreeView().getSelectionListener().addSubscriber(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem item = (TreeItem)MainFrame.getInstance().getITree().getTreeView().getLastSelectedPathComponent();
        RenamePopup popup = new RenamePopup(item);
    }

    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof Workspace ||
        notification.getNotificationObject() instanceof Page)
            setEnabled(false);
        else
            setEnabled(true);
    }
}
