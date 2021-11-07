package app.controller.actions;

import app.controller.popup.InformationPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends MyAbstractAction {

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/informationSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/information.png"));
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Information about the softwere");
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        InformationPopup dialog = new InformationPopup();
    }
}
