package app.view.tree;


import app.model.node.NodeModel;
import app.model.repository.Workspace;
import app.view.tree.controller.SelectionListener;
import app.view.tree.model.TreeItem;
import app.view.tree.view.TreeView;

import javax.swing.*;

import javax.swing.tree.MutableTreeNode;

public interface ITree {

    JTree generateTreeView(Workspace workspace);
    TreeItem getSelectedTreeItem();
    TreeItem findItemByModel(NodeModel node);

    TreeView getTreeView();
    TreeItem getRoot();
    TreeItem setRootSelected();
    SelectionListener getSelectionListener();


}
