package app.controller.actions;

import app.observer.Notification;
import app.view.gui.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends MyAbstractAction {

    public RedoAction() {
        putValue(SHORT_DESCRIPTION,"Redo.");
        putValue(LARGE_ICON_KEY,loadIcon("images/Arrows-Redo-icon.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();
    }

    @Override
    public void update(Notification notification) {


    }
}
