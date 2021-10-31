package app.controller.actions;

import javax.swing.*;
import java.awt.*;
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

    public Image getImage(String fileName){
        URL imageURL = getClass().getResource(fileName);


        if(imageURL != null)
            return new ImageIcon(imageURL).getImage();

        System.out.println("file not found");
        return null;
    }
}
