package app.controller.actions;

import app.controller.popup.SetAuthorPopup;
import app.controller.popup.SetThemePopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditDocumentAction extends MyAbstractAction {



    public EditDocumentAction(){

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/pictureSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/pictureLarge.png"));
        putValue(NAME,"Set theme");
        putValue(SHORT_DESCRIPTION,"Set theme for selected document.");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        SetThemePopup popup = new SetThemePopup();

    }
}
