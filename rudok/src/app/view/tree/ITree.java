package app.view.tree;

import app.model.repository.Workspace;

import javax.swing.*;

public interface ITree {

    JTree gerateTreeView(Workspace workspace);

}
