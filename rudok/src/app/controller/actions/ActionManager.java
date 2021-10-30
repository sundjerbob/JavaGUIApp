package app.controller.actions;

import app.controller.actions.InfoAction;
import app.controller.actions.NewAction;

public class ActionManager {

    private NewAction newAction;
    private InfoAction infoAction;

    public ActionManager(){
        newAction = new NewAction();
        infoAction = new InfoAction();
    }


    public NewAction getNewAction(){
        return newAction;
    }
    public InfoAction getInfoAction(){
        return infoAction;
    }

}
