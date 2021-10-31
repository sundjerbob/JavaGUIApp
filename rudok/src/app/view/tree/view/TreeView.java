package app.view.tree.view;

import app.view.tree.controller.CellRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class TreeView extends JTree {


    public TreeView(TreeModel treeModel){
        setOpaque(false);
     setModel(treeModel);
     setCellRenderer(new CellRenderer());
     UIManager.put("Tree.background", Color.cyan);

    }
}
