package app.controller.actions;
import app.observer.Notification;
import java.awt.event.ActionEvent;

public class DelSlotStateAction extends MyAbstractAction{


    public DelSlotStateAction(){
        putValue(LARGE_ICON_KEY, loadIcon("images/eraser.png"));
        putValue(NAME,"Eraser");
        putValue(SHORT_DESCRIPTION,"Eraser");
    }

    @Override
    public void update(Notification notification) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
