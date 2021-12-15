package app.controller.actions;


import app.observer.Notification;
import app.view.gui.MainFrame;
import app.view.repository.DocumentView;
import app.view.repository.WorkspaceView;
import app.view.state.SlideshowState;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeModeAction extends MyAbstractAction{

    public  ChangeModeAction() {

        //putValue(SMALL_ICON, loadIcon());
        putValue(LARGE_ICON_KEY, loadIcon("images/slideshow.png"));
        //putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Enter slide show mode.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(WorkspaceView.getCurrentlyOpened() instanceof DocumentView){
            System.out.println("jjjsajsdnajsdna");
            DocumentView doc = ((DocumentView) WorkspaceView.getCurrentlyOpened());
            doc.switchMode();
            if(doc.getStateManager().getCurrModeState() instanceof SlideshowState) {
                putValue(LARGE_ICON_KEY, loadIcon("images/editMode.png"));
                putValue(SHORT_DESCRIPTION, "Enter edit mode.");
            }
            else {
                putValue(LARGE_ICON_KEY,loadIcon("images/slideshow.png"));
                putValue(SHORT_DESCRIPTION,"Enter slide show mode.");
            }
        }

    }

    @Override
    public void update(Notification notification) {}
}
