package app.controller.actions;

import app.controller.command.AddCommand;
import app.model.node.NodeComposite;
import app.model.repository.Page;
import app.observer.Notification;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends MyAbstractAction {


    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.SHIFT_MASK,true));
        putValue(SMALL_ICON, loadIcon("images/new.small.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/new.png"));
        putValue(NAME,"New");
        putValue(SHORT_DESCRIPTION,"New");
        MainFrame.getInstance().getITree().getTreeView().getSelectionListener().addSubscriber(this);
    }

     @Override
     public void actionPerformed(ActionEvent e) {

         MainFrame frame = MainFrame.getInstance();
         TreeItem item = frame.getITree().getSelectedTreeItem();

         if (item.getModel() instanceof NodeComposite)
            MainFrame.getInstance().getCommandManager().addCommand(new AddCommand((NodeComposite) item.getModel()));

     }


    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof Page)
            setEnabled(false);
        else
            setEnabled(true);
    }

}
