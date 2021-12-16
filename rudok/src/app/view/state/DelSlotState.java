package app.view.state;

import app.view.repository.DocumentView;

import java.awt.event.MouseEvent;

public class DelSlotState implements IDrawState{

    private DocumentView document;

    public DelSlotState(DocumentView documentView){
        document = documentView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
