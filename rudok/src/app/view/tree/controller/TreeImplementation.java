package app.view.tree.controller;

import app.model.node.NodeComposit;
import app.model.node.NodeModel;
import app.model.repository.Workspace;
import app.view.tree.ITree;
import app.view.tree.model.TreeItem;
import app.view.tree.view.TreeView;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TreeImplementation implements ITree {

    private TreeView treeView;
    private TreeModel treeModel;
    private TreeItem root;


    @Override
    public JTree generateTreeView(Workspace workspace) {
        root = new TreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new TreeView(treeModel);
        treeView.setSelectionPath(new TreePath(root));
        treeView.setExpandsSelectedPaths(true);
        return treeView;
    }


    @Override
    public void addNew(NodeModel newNode) {
        TreeItem item = (TreeItem) treeView.getLastSelectedPathComponent();
        item.add(new TreeItem(newNode));
        treeView.updateUI();
    }

    @Override
    public void delete(TreeItem node) {
        TreeItem delete = (TreeItem) node;
        ((TreeItem)delete.getParent()).remove(delete);
        treeView.updateUI();
    }

    @Override
    public TreeItem getSelectedTreeItem() {
        return (TreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public TreeItem setRootSelected(){
        treeView.setSelectionPath(new TreePath(root));
        return root;
    }

    @Override
    public TreeView getTreeView() {
        return treeView;
    }

    @Override
    public TreeItem getRoot() {
        return root;
    }

    @Override
    public TreeItem findItemByModel(NodeModel node){
        if(node instanceof Workspace){
            return root;
        }

        TreeItem item = findItemByModel(node.getParent());
        return (TreeItem) item.getChildAt(node.getParent().getChildren().indexOf(node));
    }
}
