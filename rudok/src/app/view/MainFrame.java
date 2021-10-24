package app.view;

import app.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private ToolBar toolBar;
    private ActionManager actionManager;

    private MainFrame(){}

    public static MainFrame getInstance(){
        if(instance == null)
            instance = new MainFrame();
        return instance;
    }

    public void init(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        d.width /= 2;
        d.height /= 2;
        setSize(d);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
        JScrollPane left = new JScrollPane();
        JPanel right = new JPanel();
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JSplitPane split  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,right);
        split.setDividerLocation(d.width/4);
        actionManager = new ActionManager();
        toolBar = new ToolBar();
        mainPanel.add(split);
        mainPanel.add(toolBar, BorderLayout.NORTH);



    }

    public ToolBar getToolBar(){
        return toolBar;
    }

    public  ActionManager getActionManager(){
        return actionManager;
    }

}

