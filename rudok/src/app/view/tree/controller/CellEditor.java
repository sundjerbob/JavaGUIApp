package app.view.tree.controller;

import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

public class CellEditor extends DefaultTreeCellEditor implements ActionListener {


    public CellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);

    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
      super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);


        JTextField text = new JTextField(((TreeItem)value).getModel().getName());
        text.addActionListener(this);
        text.setOpaque(true);
        text.setDisabledTextColor(Color.RED);
        text.setBackground(Color.cyan);

        return text;
    }

    @Override
    public boolean isCellEditable(EventObject arg0) {
        //System.out.println("MORE LIFE G");

        if (arg0 instanceof MouseEvent) {
            if (((MouseEvent) arg0).getClickCount() == 3) {

                return true;
            }

        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {

        TreeItem item = MainFrame.getInstance().getiTree().getSelectedTreeIteam();
        item.getModel().setName(e.getActionCommand());
        item.setName(e.getActionCommand());
        MainFrame.getInstance().getiTree().getTreeView().updateUI();
    }



}
