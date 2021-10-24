package app.controller;

import javax.swing.*;
import java.net.StandardSocketOptions;
import java.net.URL;

public abstract class MyAbstractAction extends AbstractAction {

    public Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("file not found");
        return icon;
    }
}
