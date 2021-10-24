package app.controller;

import app.controller.MyAbstractAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends MyAbstractAction {


    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/documentSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/document.png"));
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Information about the softwere");

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
