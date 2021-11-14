package app.controller.actions;

import app.controller.popup.SetAuthorPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SetAuthorAction extends MyAbstractAction{

    public SetAuthorAction(){

        putValue(SMALL_ICON, loadIcon("images/authorSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/authorLarge.png"));
        putValue(NAME,"Rename");
        putValue(SHORT_DESCRIPTION,"Rename");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SetAuthorPopup popup = new SetAuthorPopup();
    }
}
