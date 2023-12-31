package app.view.tree.view;

import app.model.repository.Document;
import app.model.repository.File;
import app.model.repository.Page;
import app.model.repository.Workspace;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;


public class CellRenderer extends DefaultTreeCellRenderer  {


    public CellRenderer(){


    }

    @Override
    public Component getTreeCellRendererComponent
            (JTree treeView,
             Object value,
             boolean sel,
             boolean expanded,
             boolean leaf,
             int row,
             boolean hasFocus){
        super.getTreeCellRendererComponent(treeView,value,sel,expanded,leaf,row,hasFocus);


        // coloring of the tree cells if they are not selected
        setOpaque(true);
        if (sel) {
            setForeground(Color.cyan);
        }
        else {
            setBackground(Color.CYAN.darker());
        }

        setBorder(new EmptyBorder(2,2,2,2));

        if(((TreeItem)value).getModel() instanceof Workspace)
            setIcon(getIcon("icons/workspace.png"));
        else if(((TreeItem)value).getModel() instanceof File && expanded)
            setIcon(getIcon("icons/fileOpen.png"));
        else if(((TreeItem)value).getModel() instanceof  File && !expanded)
            setIcon(getIcon("icons/file.png"));
        else if(((TreeItem)value).getModel() instanceof Document)
            setIcon(getIcon("icons/presentation.png"));
        else if(((TreeItem)value).getModel() instanceof Page)
            setIcon(getIcon("icons/page.png"));
        return this;
    }

    private Icon getIcon(String path){

        URL imageURL = getClass().getResource(path);
        if(imageURL != null)
            return new ImageIcon(imageURL);
        System.out.println("Cell renderer no icon");
        return null;
    }



}
