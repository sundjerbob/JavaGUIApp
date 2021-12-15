package app.controller.actions;

public class ActionManager {

    private final NewAction newAction;
    private final DeleteAction deleteAction;
    private final InfoAction infoAction;
    private final RenameAction renameAction;
    private final EditDocumentAction editDocumentAction;
    private final SetAuthorAction setAuthorAction;
    private final ChangeModeAction changeModeAction;

    public ActionManager(){
        newAction = new NewAction();
        deleteAction = new DeleteAction();
        infoAction = new InfoAction();
        renameAction = new RenameAction();
        editDocumentAction = new EditDocumentAction();
        setAuthorAction = new SetAuthorAction();
        changeModeAction = new ChangeModeAction();
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

    public EditDocumentAction getEditDocumentAction() {
        return editDocumentAction;
    }

    public SetAuthorAction getSetAuthorAction(){
        return setAuthorAction;
    }

    public ChangeModeAction getChangeModeAction(){
        return changeModeAction;
    }
}
