package app.controller.actions;

import app.model.repository.File;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends MyAbstractAction{

    public DeleteAction(){
        putValue(ACCELERATOR_KEY,KeyEvent.VK_DELETE);
        putValue(SMALL_ICON, loadIcon("images/deleteSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/delete.png"));
        putValue(NAME,"Delete");
        putValue(SHORT_DESCRIPTION,"Delete selected component");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame frame = MainFrame.getInstance();
        TreeItem item = frame.getiTree().getSelectedTreeIteam();


            item.getModel().getParent().removeChild(item.getModel());
            frame.getiTree().delete(item);
            frame.getiTree().setRootSelected();


    }
}
