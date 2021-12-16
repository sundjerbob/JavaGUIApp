package app.controller.actions;
import app.model.repository.Workspace;
import app.observer.Notification;
import app.view.repository.DocumentView;
import app.view.repository.WorkspaceView;

import java.awt.event.ActionEvent;

public class AddSlotStateAction extends MyAbstractAction{

    public AddSlotStateAction() {

        putValue(LARGE_ICON_KEY, loadIcon("images/square.png"));
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Rename");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((DocumentView)WorkspaceView.getCurrentlyOpened()).getStateManager().setAddSlotState();
    }

    @Override
    public void update(Notification notification) {

    }
}
