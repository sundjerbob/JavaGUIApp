package app.controller.actions;
import app.observer.Notification;
import java.awt.event.ActionEvent;


public class SelectStateAction extends MyAbstractAction{

    public SelectStateAction() {
        putValue(SMALL_ICON, loadIcon("images/editingSmall.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/point.png"));
        putValue(NAME,"Select");
        putValue(SHORT_DESCRIPTION,"Select");
    }
    @Override
    public void update(Notification notification) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
