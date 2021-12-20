package app.controller.actions;

import app.observer.Notification;
import app.view.repository.DocumentView;
import app.view.repository.WorkspaceView;

import java.awt.event.ActionEvent;

public class AddSlotStateAction extends MyAbstractAction{

    public AddSlotStateAction() {

        putValue(LARGE_ICON_KEY, loadIcon("images/pen.png"));

        putValue(SHORT_DESCRIPTION, "Draw a square.");


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DocumentView doc = (DocumentView)WorkspaceView.getCurrentlyOpened();
        doc.setDrawState(doc.getStateManager().getAddSlotState());
    }

    @Override
    public void update(Notification notification) {

    }
}
