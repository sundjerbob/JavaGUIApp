package app.view.tree.controller;

import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoubleClickListener extends MouseAdapter {




    @Override
    public void mouseClicked(MouseEvent e) {

        JTree myTree = MainFrame.getInstance().getITree().getTreeView();
        int itemRow = myTree.getRowForLocation(e.getX(),e.getY());
        if(itemRow != -1 && e.getClickCount() == 2){
            TreeItem item = (TreeItem) myTree.getLastSelectedPathComponent();
            item.getModel().notifySubscribers(new Notification(item.getModel(),
                    NotificationType.DOUBLE_CLICK));
        }
    }
}