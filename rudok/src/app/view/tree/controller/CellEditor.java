package app.view.tree.controller;

import app.controller.command.RenameCommand;
import app.model.node.NodeComposite;
import app.model.repository.Page;
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

    private JTextField text;
    private TreeItem item;

    public CellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);

    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {

        super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);

        item = (TreeItem)value;


        text = new JTextField(item.getModel().getName());
        text.addActionListener(this);
        text.setOpaque(true);

        text.setBackground(Color.cyan);

        return text;
    }

    @Override
    public boolean isCellEditable(EventObject arg0) {
        //System.out.println("MORE LIFE G");

        if (arg0 instanceof MouseEvent) {

            if (((MouseEvent) arg0).getClickCount() == 3) {
                TreeItem item = (TreeItem) MainFrame.getInstance().getITree().getTreeView().getLastSelectedPathComponent();
                if(item.getModel() instanceof Page){
                    return false;
                }
                return true;
            }
        }
        return false;

    }

    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand(e.getActionCommand(), (NodeComposite) item.getModel()));
    }



}
