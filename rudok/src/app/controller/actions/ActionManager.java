package app.controller.actions;

import app.controller.actions.InfoAction;
import app.controller.actions.NewAction;

public class ActionManager {

    private static ActionManager actionManager;
    private NewAction newAction;
    private DeleteAction deleteAction;
    private InfoAction infoAction;

    private ActionManager(){
        newAction = new NewAction();
        deleteAction = new DeleteAction();
        infoAction = new InfoAction();

    }

    public static ActionManager getActionManager() {
        if(actionManager == null)
            actionManager = new ActionManager();
        return actionManager;
    }

    public NewAction getNewAction(){
        return newAction;
    }

    public  DeleteAction getDeleteAction(){
        return deleteAction;
    }

    public InfoAction getInfoAction(){
        return infoAction;
    }

}
