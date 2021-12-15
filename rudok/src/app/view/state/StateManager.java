package app.view.state;

import app.view.repository.DocumentView;

public class StateManager {

    private final DocumentView document;
    private IModeState currModeState;
    private IDrawState currDrawState;

    private final EditState editState;
    private final SlideshowState slideshowState;

    public StateManager(DocumentView doc) {
        document = doc;
        editState = new EditState( doc);
        slideshowState = new SlideshowState(doc);
        currModeState = editState;
    }

    public void setEditState() {
        if(currModeState != editState) {
            currModeState = editState;
        }
        currModeState.set();
    }
    public void setSlideshowState(){
        if(currModeState != slideshowState) {
            currModeState = slideshowState;
        }
        currModeState.set();

    }

    public IModeState getCurrModeState(){
        return currModeState;
    }

    public IDrawState getCurrDrawState(){
        return currDrawState;
    }
}