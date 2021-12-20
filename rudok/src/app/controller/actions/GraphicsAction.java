package app.controller.actions;
import app.controller.popup.GraphicPopup;
import app.observer.Notification;
import app.view.repository.DocumentView;
import app.view.repository.WorkspaceView;
import java.awt.event.ActionEvent;


public class GraphicsAction extends MyAbstractAction{
    public GraphicsAction() {
        putValue(LARGE_ICON_KEY, loadIcon("images/color-picker.png"));

        putValue(SHORT_DESCRIPTION,"Graphic settings");
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        DocumentView doc = (DocumentView) WorkspaceView.getCurrentlyOpened();
        doc.setDrawState(this);
        GraphicPopup popup = new GraphicPopup(doc);
        doc.setDrawState(doc.getStateManager().getCurrDrawState());
    }




    @Override
    public void update(Notification notification) {


    }


}
