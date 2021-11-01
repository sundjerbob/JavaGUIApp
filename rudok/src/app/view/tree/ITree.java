package app.view.tree;


import app.model.node.NodeModel;
import app.model.repository.Workspace;
import app.view.tree.model.TreeItem;

import javax.swing.*;

import javax.swing.tree.MutableTreeNode;

public interface ITree {

    JTree gerateTreeView(Workspace workspace);
    TreeItem getSelectedTreeIteam();

    void addNew(NodeModel node);
    void delite(NodeModel node);

}
