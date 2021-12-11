package app.view.state;

public class StateManager {

    private IModeState currModeState;
    private IDrawState currDrawState;

    private EditState editState;
    private SlideshowState slideshowState;

    public StateManager() {
    }


    public IModeState getCurrModeState() {
        return currModeState;
    }

    public IDrawState getCurrDrawState() {
        return currDrawState;
    }

    public void setEditState(){
        if(editState == null)
            editState = new EditState();
        currModeState = editState;
    }

    public void setSlideshowState(){
        if(slideshowState == null)
            slideshowState = new SlideshowState();
        currModeState = slideshowState;
    }

    public SlideshowState getSlideshowState() {
        return slideshowState;
    }

    public EditState getEditState() {
        return editState;
    }
}
