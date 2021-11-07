package app.view.tree.view;

import app.view.tree.controller.CellEditor;
import app.view.tree.controller.SelectionListener;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TreeView extends JTree implements MouseListener {

    private TreeItem root;

    public TreeView(TreeModel treeModel){

     setBorder(new EmptyBorder(8,8, 10,0));
     root = (TreeItem) treeModel.getRoot();
     setModel(treeModel);
     setRootVisible(false);
     CellRenderer r = new CellRenderer();
     setCellRenderer(r);
     setBackground(Color.cyan.darker());
     addTreeSelectionListener(new SelectionListener());
     setCellEditor(new CellEditor(this,r));
     setEditable(true);
     addMouseListener(this);

    }

 @Override
 public void mouseClicked(MouseEvent e) {
     if(getRowForLocation(e.getX(),e.getY()) == -1)
      setSelectionPath(new TreePath(root));
 }

 @Override
 public void mousePressed(MouseEvent e) {


 }

 @Override
 public void mouseReleased(MouseEvent e) {

 }

 @Override
 public void mouseEntered(MouseEvent e) {

 }

 @Override
 public void mouseExited(MouseEvent e) {

 }
}
