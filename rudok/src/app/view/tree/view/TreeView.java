package app.view.tree.view;

import javax.swing.*;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class TreeView extends JTree {


    public TreeView(TreeModel treeModel){

        setOpaque(false);
     setModel(treeModel);
     setCellRenderer(new CellRenderer());
        setBackground(Color.cyan);

    }
}
