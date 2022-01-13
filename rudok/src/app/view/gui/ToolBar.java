package app.view.gui;



import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar(){

        setBackground(Color.cyan.darker());

        add(new Button(MainFrame.getInstance().getActionManager().getNewAction()));
        add(new Button(MainFrame.getInstance().getActionManager().getDeleteAction()));
        add(new Button(MainFrame.getInstance().getActionManager().getRenameAction()));
        add(new Button(MainFrame.getInstance().getActionManager().getSetThemeAction()));
        add(new Button(MainFrame.getInstance().getActionManager().getSetAuthorAction()));
        add(new Button(MainFrame.getInstance().getActionManager().getShareAction()));
        add(new Button(MainFrame.getInstance().getActionManager().getInfoAction()));
        addSeparator(new Dimension(4, 10));
        add(new Button(MainFrame.getInstance().getActionManager().getUndoAction()));
        add(new Button( MainFrame.getInstance().getActionManager().getRedoAction()));

        setFloatable(false);

    }


}
