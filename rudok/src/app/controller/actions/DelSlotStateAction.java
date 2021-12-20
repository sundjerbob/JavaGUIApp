package app.controller.actions;
import app.observer.Notification;
import app.view.repository.DocumentView;
import app.view.repository.WorkspaceView;

import java.awt.event.ActionEvent;

public class DelSlotStateAction extends MyAbstractAction{


    public DelSlotStateAction(){
        putValue(LARGE_ICON_KEY, loadIcon("images/eraserFinal.png"));

        putValue(SHORT_DESCRIPTION,"Erase.");
    }

    @Override
    public void update(Notification notification) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DocumentView doc = (DocumentView)WorkspaceView.getCurrentlyOpened();
        doc.setDrawState(doc.getStateManager().getDelSlotState());
    }
}
