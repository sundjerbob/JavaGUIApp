package app.view.gui;

import app.view.gui.MainFrame;

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
        add(new Button(MainFrame.getInstance().getActionManager().getInfoAction()));
        setFloatable(false);

    }


}
