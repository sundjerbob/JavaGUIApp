package app.controller.actions;

import app.observer.Notification;
import app.view.gui.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends MyAbstractAction {

    public UndoAction() {
        putValue(SHORT_DESCRIPTION,"Undo.");
        putValue(LARGE_ICON_KEY, loadIcon("images/Arrows-Undo-icon.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().undoCommand();

    }


    @Override
    public void update(Notification notification) {

    }

}
