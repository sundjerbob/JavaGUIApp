package app.controller.actions;

import app.controller.actions.InfoAction;
import app.controller.actions.NewAction;

public class ActionManager {

    private NewAction newAction;
    private DeleteAction deleteAction;
    private InfoAction infoAction;
    private RenameAction renameAction;

    public ActionManager(){
        newAction = new NewAction();
        deleteAction = new DeleteAction();
        infoAction = new InfoAction();
        renameAction = new RenameAction();

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

    public RenameAction getRenameAction() {
        return renameAction;
    }
}
