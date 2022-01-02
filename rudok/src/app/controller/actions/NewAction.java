package app.controller.actions;

import app.factory.Factory;
import app.model.node.NodeComposite;
import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.File;
import app.model.repository.Page;
import app.model.repository.Workspace;
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
         NodeModel newNode;
         String name;

         if (item.getModel() instanceof NodeComposite)
             Factory.getMe().getFactory((NodeComposite) item.getModel()).create();


     }


    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof Page)
            setEnabled(false);
        else
            setEnabled(true);
    }

}
