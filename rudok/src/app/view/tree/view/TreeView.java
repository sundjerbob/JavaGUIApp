package app.view.tree.view;

import app.view.tree.controller.CellEditor;
import app.view.tree.controller.DoubleClickListener;
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


    private final SelectionListener selectionListener;

     public TreeView(TreeModel treeModel){
     setBorder(new EmptyBorder(8,8, 10,0));
     setBackground(Color.cyan.darker());

     setModel(treeModel);
     setRootVisible(false);

     selectionListener = new SelectionListener();
     addTreeSelectionListener(selectionListener);

     CellRenderer cellRenderer = new CellRenderer();
     setCellRenderer(cellRenderer);
     setCellEditor(new CellEditor(this,cellRenderer));
     setEditable(true);

     addMouseListener(this);
     addMouseListener(new DoubleClickListener());
     }

     @Override
     public void mouseClicked(MouseEvent e) {
       if(getRowForLocation(e.getX(),e.getY()) == -1)
       setSelectionPath(new TreePath(getModel().getRoot()));
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

     public SelectionListener getSelectionListener() {
     return selectionListener;
     }


}
