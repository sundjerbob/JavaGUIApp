package app.view.state;

import app.model.repository.Page;
import app.model.repository.Slot;
import app.view.repository.DocumentView;
import app.view.repository.PageView;

import java.awt.event.MouseEvent;

public class AddSlotState implements  IDrawState{

    private final DocumentView document;

    public AddSlotState(DocumentView documentView){
        document = documentView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        PageView p = document.getPages().get(document.getCurrentPage());
        ((Page)p.getModel()).addSlot(e.getPoint());
    }
}
