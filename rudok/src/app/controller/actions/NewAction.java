package app.controller.actions;

import app.model.node.NodeComposite;
import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.File;
import app.model.repository.Page;
import app.model.repository.Workspace;
import app.observer.Notification;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends MyAbstractAction {


    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/new.small.png"));
        putValue(LARGE_ICON_KEY, loadIcon("images/new.png"));
        putValue(NAME,"New");
        putValue(SHORT_DESCRIPTION,"New");
        MainFrame.getInstance().getITree().getTreeView().getSelectionListener().addSubscriber(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame frame = MainFrame.getInstance();
        TreeItem item = frame.getITree().getSelectedTreeItem();
        NodeModel newNode = null;
        String name;

        if(item.getModel() instanceof Workspace){
            name = getName((Workspace)item.getModel(),"File");
            newNode = new File(name,(Workspace) item.getModel());
            ((Workspace) item.getModel()).addChild(newNode);
        }
        else if(item.getModel() instanceof File){
            name = getName((File)item.getModel(),"Presentation");
            newNode = new Document(name,(File) item.getModel());
            ((File) item.getModel()).addChild(newNode);
        }
        else if(item.getModel() instanceof Document){
            name = getName((Document)item.getModel(),"Slide");
            newNode = new Page(name,(Document) item.getModel());
            ((Document) item.getModel()).addChild(newNode);

        }
    }

    public String getName(NodeComposite model,String name){
        int num = 1;
        while(model.getChildByName(name + " " + num) != null) {
            ++num;
        }
        return name + " " + num;
    }

    @Override
    public void update(Notification notification) {
        if(notification.getNotificationObject() instanceof Page)
            setEnabled(false);
        else
            setEnabled(true);
    }
}
