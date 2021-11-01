package app.view.tree.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class TreeView extends JTree {


    public TreeView(TreeModel treeModel){

     setBorder(new EmptyBorder(8,8, 10,0));
        setOpaque(false);
     setModel(treeModel);
     setRootVisible(false);
     setCellRenderer(new CellRenderer());
     setBackground(Color.cyan);

    }
}
