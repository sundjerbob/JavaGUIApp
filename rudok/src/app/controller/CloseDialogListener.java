package app.controller;

import app.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseDialogListener extends WindowAdapter {

    private MainFrame mainFrame;

    public CloseDialogListener(MainFrame mainFrame){
    this.mainFrame = mainFrame;
    }

    @Override
    public void windowClosing(WindowEvent e) {

        int out = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to exit aplication?","Close dialog",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(out == JOptionPane.NO_OPTION)
            mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        else
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
