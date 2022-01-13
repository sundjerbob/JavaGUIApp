package app.controller.command;

import app.view.gui.MainFrame;

import java.util.ArrayList;

public class CommandManager {

    private int currCommand;
    private ArrayList<ICommand> commandStack;

    public CommandManager() {
        currCommand = 0;
        commandStack = new ArrayList<>();
    }

    public void addCommand( ICommand newCommand ) {
        while (currCommand < commandStack.size()) {//breaks when currCommand == commandStack.size() is true
            commandStack.remove(currCommand);
        }
        commandStack.add(newCommand);//ads 1 to commandStack size so now commandStack.size() == currentCommand + 1
        doCommand();
    }

    public void doCommand() {
        if(currCommand < commandStack.size()) {//cuz commandStack.size() == currentCommand + 1 is true every time
            commandStack.get(currCommand++).redo();//increment currCommand
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }
        if(currCommand == commandStack.size()) {//if we pop last command currCommand index becomes out of range
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);//so we turn off redo
            System.out.println("");
        }

    }

    public void undoCommand(){
        if(currCommand > 0) {//has stuff to undo
            commandStack.get(--currCommand).undo();//pulling index back so we give redo +1 range
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);

        }
        if(currCommand == 0)//first command has been undone, there were no commands before that
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

}
