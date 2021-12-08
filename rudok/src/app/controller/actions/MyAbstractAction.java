package app.controller.actions;

import app.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.net.StandardSocketOptions;
import java.net.URL;

public abstract class MyAbstractAction extends AbstractAction implements ISubscriber {

    protected Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("MyAbstractAction - load icon failed");
        return icon;
    }
}
