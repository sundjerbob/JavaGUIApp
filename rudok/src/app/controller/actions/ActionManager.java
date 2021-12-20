package app.controller.actions;

public class ActionManager {

    private final NewAction newAction;
    private final DeleteAction deleteAction;
    private final InfoAction infoAction;
    private final RenameAction renameAction;
    private final SetThemeAction setThemeAction;
    private final SetAuthorAction setAuthorAction;
    private final ChangeModeAction changeModeAction;
    private final AddSlotStateAction addSlotStateAction;
    private final DelSlotStateAction delSlotStateAction;
    private final SelectStateAction selectStateAction;
    private final GraphicsAction graphicsAction;

    public ActionManager(){
        newAction = new NewAction();
        deleteAction = new DeleteAction();
        infoAction = new InfoAction();
        renameAction = new RenameAction();
        setThemeAction = new SetThemeAction();
        setAuthorAction = new SetAuthorAction();
        changeModeAction = new ChangeModeAction();
        addSlotStateAction = new AddSlotStateAction();
        delSlotStateAction = new DelSlotStateAction();
        selectStateAction = new SelectStateAction();
        graphicsAction = new GraphicsAction();
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

    public SetThemeAction getSetThemeAction() {
        return setThemeAction;
    }
    public SetAuthorAction getSetAuthorAction(){
        return setAuthorAction;
    }

    public ChangeModeAction getChangeModeAction(){
        return changeModeAction;
    }

    public AddSlotStateAction getAddSlotStateAction() {
        return addSlotStateAction;
    }

    public DelSlotStateAction getDelSlotStateAction() {
        return delSlotStateAction;
    }

    public SelectStateAction getSelectStateAction() {
        return selectStateAction;
    }

    public GraphicsAction getGraphicsAction() {
        return graphicsAction;
    }

}



