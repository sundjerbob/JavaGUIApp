package app.view.state;

import app.view.repository.DocumentView;

import java.awt.event.MouseEvent;

public class SelectState implements IDrawState {
    private DocumentView document;
    public SelectState (DocumentView documentView){
        document = documentView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
