package app.factory;

import app.view.gui.MainFrame;

import javax.swing.*;

public class NameAlrExists {

    public NameAlrExists(){

    }


    public void showError(JDialog parent){
        System.out.println("mmm");
        JOptionPane.showMessageDialog(parent,"u can not set nothing for a name");
    }

}
