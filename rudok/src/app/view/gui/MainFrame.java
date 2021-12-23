package app.view.gui;

import app.controller.actions.ActionManager;
import app.controller.popup.ClosePopup;
import app.model.repository.Workspace;
import app.view.repository.WorkspaceView;
import app.view.tree.ITree;
import app.view.tree.controller.TreeImplementation;
import app.view.tree.view.TreeView;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {


    private static MainFrame instance = null;
    private final Color color = Color.cyan;
    private ITree iTree;
    private ActionManager actionManager;
    private JSplitPane split;




    private MainFrame() { }

    public static MainFrame getInstance() {
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
        ToolBar toolBar = new ToolBar();
        JMenuBar menuBar = new MenuBar();


        //Main app window settings

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        d.width = (int)(d.width * 0.75);
        d.height = (int)(d.height * 0.75);

        setSize(d);
        setLocationRelativeTo(null);

        //listener for custom close popup

        addWindowListener(new ClosePopup(this));

        //making the components layout

        //left side for tree view
        JPanel left = new JPanel(new BorderLayout());

        URL url = getClass().getResource("../repository/images/workspace.png");
        JLabel label;
        if(url != null)
            label = new JLabel("Workspace",new ImageIcon(url),SwingConstants.CENTER);
        else{
            System.out.println("url is null");
            return;
        }

        left.add(label,BorderLayout.NORTH);

        label.setFont(new Font(label.getFont().getName(),Font.BOLD,15));
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
        split  = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, workspaceView);
        split.setDividerLocation(getSize().width / 5);

        add(toolBar,BorderLayout.NORTH);        //adding toolbar to north
        add(split,BorderLayout.CENTER);         //adding splitPane view to center
        setJMenuBar(menuBar);
        setVisible(true);
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
}

