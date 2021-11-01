package app.view.gui;

import app.controller.actions.ActionManager;
import app.controller.CloseDialogListener;
import app.model.repository.Workspace;
import app.view.tree.ITree;
import app.view.tree.TreeImplementation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private Color color = Color.cyan;
    private ToolBar toolBar;
    private ActionManager actionManager;
    private JMenuBar menuBar;
    private ITree tree;


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

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        d.width /= 2;
        d.height /= 2;
        setSize(d);
        setLocationRelativeTo(null);


        //listener for custom close popup

        addWindowListener(new CloseDialogListener(this));


                                                                                //making the components layout
        //left side for tree view
        tree = new TreeImplementation();
        JScrollPane left = new JScrollPane(tree.gerateTreeView(new Workspace("workspace")));
        left.getViewport().setBackground(color);
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);



       //right side for work space panel
        JPanel right = new JPanel();
        right.setBackground(color);

        //splitting
        JSplitPane split  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,right);
        split.setDividerLocation(getSize().width/4);
        split.setBackground(color);

        add(toolBar,BorderLayout.NORTH);        //adding toolbar to north
        add(split,BorderLayout.CENTER);         //adding splitPane view to center
        setJMenuBar(menuBar);

    }

    public ToolBar getToolBar(){
        return toolBar;
    }

    public  ActionManager getActionManager(){
        return actionManager;
    }

    public Color getColor(){
        return color;
    }

    public ITree getTree(){
        return tree;
    }

}

