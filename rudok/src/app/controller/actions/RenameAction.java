package app.controller.actions;

import app.controller.popup.RenamePopup;
import app.view.gui.MainFrame;
import app.view.tree.controller.CellEditor;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameAction extends  MyAbstractAction{

    public RenameAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/editingSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/editing.png"));
        putValue(NAME,"Rename");
        putValue(SHORT_DESCRIPTION,"Rename");

}

    @Override
    public void actionPerformed(ActionEvent e) {
        TreeItem item = (TreeItem)MainFrame.getInstance().getITree().getTreeView().getLastSelectedPathComponent();
        RenamePopup popup = new RenamePopup(item);
    }
}
