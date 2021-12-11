package app.view.gui;

import app.controller.actions.ActionManager;
import app.controller.popup.ClosePopup;
import app.model.repository.Workspace;
import app.view.repository.WorkspaceView;
import app.view.tree.ITree;
import app.view.tree.controller.TreeImplementation;
import app.view.tree.view.TreeView;
import com.sun.source.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {


    private static MainFrame instance = null;
    private Color color = Color.cyan;
    private ITree iTree;
    private ActionManager actionManager;
    private ToolBar toolBar;
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
        iTree = new TreeImplementation();
        TreeView treeView = (TreeView) iTree.generateTreeView(new Workspace("workspace"));//new JTree
        actionManager = new ActionManager();
        toolBar = new ToolBar();
        menuBar = new MenuBar(color);


        //Main app window settings

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        d.width = (int)(d.width * 0.8);
        d.height = (int)(d.height * 0.8);
        setSize(d);
        setLocationRelativeTo(null);


        //listener for custom close popup

        addWindowListener(new ClosePopup(this));

        //making the components layout

        //left side for tree view
        JPanel left = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Workspace",getLabelIcon("../repository/images/workspace.png"),SwingConstants.CENTER);
        left.add(label,BorderLayout.NORTH);
        label.setPreferredSize( new Dimension (label.getWidth(),40) ) ;
        label.setBackground(new Color(0xC6F9F4));
        label.setOpaque(true);

        JScrollPane scroll = new JScrollPane(treeView);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        left.add(scroll,BorderLayout.CENTER);

       //right side for work space panel
        WorkspaceView workspaceView = new WorkspaceView((Workspace) iTree.getRoot().getModel());


        //rightScroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

       //splitting
        JSplitPane split  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, workspaceView);
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

    public ITree getITree(){
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

