package app.controller.actions;
import app.observer.Notification;
import app.view.repository.DocumentView;
import app.view.repository.WorkspaceView;
import app.view.state.SelectState;

import java.awt.event.ActionEvent;


public class SelectStateAction extends MyAbstractAction{

    public SelectStateAction() {
        putValue(LARGE_ICON_KEY, loadIcon("images/clicker.png"));

        putValue(SHORT_DESCRIPTION,"Select");
    }
    @Override
    public void update(Notification notification) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DocumentView doc = (DocumentView)WorkspaceView.getCurrentlyOpened();
        doc.setDrawState(doc.getStateManager().getSelectState());
    }
}
