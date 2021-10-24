package app.view;

import app.controller.*;

import javax.swing.*;
import java.util.ArrayList;

public class ToolBar extends JToolBar {

    public ToolBar(){

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        setVisible(true);

    }


}
