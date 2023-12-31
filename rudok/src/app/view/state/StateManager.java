package app.view.state;

import app.view.repository.DocumentView;

public class StateManager {


    private final DocumentView document;
    private final EditState editState;
    private final SlideshowState slideshowState;
    private final AddSlotState addSlotState;
    private final SelectState selectState;
    private final DelSlotState delSlotState;

    private IModeState currModeState;
    private IDrawState currDrawState;



    public StateManager(DocumentView doc) {
        document = doc;
        editState = new EditState(doc);
        slideshowState = new SlideshowState(doc);
        addSlotState = new AddSlotState();
        selectState = new SelectState();
        delSlotState = new DelSlotState();
        currModeState = editState;
        currDrawState = selectState;
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

    public void setAddSlotState() {
        if(currDrawState != addSlotState)
            currDrawState = addSlotState;
    }

    public void setSelectState() {
        if(currDrawState != selectState)
        currDrawState = selectState;
    }

    public void setDelSlotState(){
        if(currDrawState != delSlotState)
            currDrawState = delSlotState;
    }

    public IModeState getCurrModeState(){
        return currModeState;
    }

    public IDrawState getCurrDrawState(){
        return currDrawState;
    }

    public AddSlotState getAddSlotState() {
        return addSlotState;
    }

    public DelSlotState getDelSlotState() {
        return delSlotState;
    }

    public SelectState getSelectState() {
        return selectState;
    }
}