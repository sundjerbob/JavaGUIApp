package app.view;

import app.controller.ActionManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private Color color = new Color(109, 23, 23, 247);
    private ToolBar toolBar;
    private ActionManager actionManager;
    private JMenuBar menuBar;


    private MainFrame(){}

    public static MainFrame getInstance(){
        if(instance == null)
            instance = new MainFrame();
        return instance;
    }

    //initialising mainFrame
    public void init(){


        //Instancing objects for the components of the main window

        actionManager = new ActionManager();
        toolBar = new ToolBar(color);
        menuBar = new MenuBar(color);


        //Main app window settings

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        d.width /= 2;
        d.height /= 2;
        setSize(d);
        setLocationRelativeTo(null);



        //making the components layout

        JScrollPane left = new JScrollPane();
        left.getViewport().setBackground(color);
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        JPanel right = new JPanel();
        right.setBackground(color);


        JSplitPane split  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,right);
        split.setDividerLocation(d.width/4);
        split.setBackground(color);

        add(toolBar,BorderLayout.NORTH);
        add(split,BorderLayout.CENTER);
        setJMenuBar(menuBar);





    }

    public ToolBar getToolBar(){
        return toolBar;
    }

    public  ActionManager getActionManager(){
        return actionManager;
    }

}

