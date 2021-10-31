package app.view.tree;

import app.model.repository.Workspace;
import app.view.tree.model.TreeItem;
import app.view.tree.view.TreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class TreeImplementation implements ITree {

    private TreeView treeView;
    private TreeModel treeModel;
    private TreeItem root;


    @Override
    public JTree gerateTreeView(Workspace workspace) {
        root = new TreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);
        return treeView;
    }
}
