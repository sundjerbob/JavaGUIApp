package app.controller.actions;

public class ActionManager {

    private NewAction newAction;
    private DeleteAction deleteAction;
    private InfoAction infoAction;
    private RenameAction renameAction;
    private EditDocumentAction editDocumentAction;
    private SetAuthorAction setAuthorAction;

    public ActionManager(){
        newAction = new NewAction();
        deleteAction = new DeleteAction();
        infoAction = new InfoAction();
        renameAction = new RenameAction();
        editDocumentAction = new EditDocumentAction();
        setAuthorAction = new SetAuthorAction();
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
}
