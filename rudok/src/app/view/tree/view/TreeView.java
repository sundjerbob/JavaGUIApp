package app.view.tree.view;

import app.view.tree.controller.CellEditor;
import app.view.tree.controller.SelectionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class TreeView extends JTree {


    public TreeView(TreeModel treeModel){

     setBorder(new EmptyBorder(10,8, 10,0));

     setModel(treeModel);
     setRootVisible(false);
     CellRenderer r = new CellRenderer();
     setCellRenderer(r);
     setBackground(Color.cyan.darker());
     addTreeSelectionListener(new SelectionListener());
     setCellEditor(new CellEditor(this,r));
     setEditable(true);


    }
}
