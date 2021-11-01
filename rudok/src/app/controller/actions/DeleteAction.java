package app.controller.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends MyAbstractAction{

    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/deleteSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/delete.png"));
        putValue(NAME,"Delete");
        putValue(SHORT_DESCRIPTION,"Delete selected component");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
