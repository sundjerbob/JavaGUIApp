package app.view.gui;

import app.controller.actions.ActionManager;
import app.controller.listeners.CloseDialogListener;
import app.model.repository.Workspace;
import app.view.tree.ITree;
import app.view.tree.TreeImplementation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private Color color = Color.cyan;
    private ToolBar toolBar;
    private ActionManager actionManager;
    private JMenuBar menuBar;
    private ITree iTree;


    private MainFrame(){}

    public static MainFrame getInstance(){
        if(instance == null)
            instance = new MainFrame();
        return instance;
    }

    //initialising mainFrame
    public void init(){


        //Instancing objects for the components of the main window

        actionManager = ActionManager.getActionManager();
        toolBar = new ToolBar();
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
        JLabel label = new JLabel("Workspace",getLabelIcon("workspace.png"),SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(6,0,6,0));
        label.setBackground(new Color(0xC6F9F4));
        label.setOpaque(true);

        ////
        JPanel left = new JPanel(new BorderLayout());
        left.add(label,BorderLayout.NORTH);

        /////
        iTree = new TreeImplementation();
        JScrollPane scroll = new JScrollPane(iTree.generateTreeView(new Workspace("workspace")));

        scroll.getViewport().setBackground(Color.black);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        left.add(scroll,BorderLayout.CENTER);


       //right side for work space panel
        JPanel right = new JPanel();
        right.setBackground(new Color(0xC6F9F4));

        //splitting
        JSplitPane split  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,right);
        split.setDividerLocation(getSize().width/4);

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

    public ITree getiTree(){
        return iTree;
    }

    private ImageIcon getLabelIcon(String path){
        URL url = getClass().getResource(path);
        if(url == null) {
            System.out.println("problem");
            return null;
        }
        return new ImageIcon(url);
    }
}

