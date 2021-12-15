package app.view.state;

import app.view.repository.DocumentView;

public class SlideshowState implements IModeState{

    private  final  DocumentView document;

    public SlideshowState(DocumentView d){
        document = d;
    }
    @Override
    public void set() {
        document.setModeState(DocumentView.SLIDE_SHOW);
    }
}
