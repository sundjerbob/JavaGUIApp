package app.controller;

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
