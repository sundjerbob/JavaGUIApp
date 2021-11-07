package app.controller.popup;

import app.view.gui.MainFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClosePopup extends WindowAdapter {

    private MainFrame mainFrame;

    public ClosePopup(MainFrame mainFrame){
    this.mainFrame = mainFrame;
    }

    @Override
    public void windowClosing(WindowEvent e) {

        int out = JOptionPane.showConfirmDialog(mainFrame,"Are you sure you want to exit aplication?","Closing?",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(out == JOptionPane.YES_OPTION)
            mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        else
            mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }


}
