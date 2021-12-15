package app.view.state;

import app.view.repository.DocumentView;

public class EditState implements  IModeState{

    private final DocumentView document;
    public EditState(DocumentView d){
        document = d;
    }
    @Override
    public void set() {
        document.setModeState(DocumentView.EDIT_MODE);
    }
}
