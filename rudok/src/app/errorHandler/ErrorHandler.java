package app.errorHandler;

import app.view.gui.MainFrame;
import javax.swing.*;

public  class ErrorHandler {


    private static ErrorHandler instance;

    private ErrorHandler() {}

    public static ErrorHandler getInstance() {
        if(instance == null)
            instance = new ErrorHandler();
        return instance;
    }

    public void createPopup(ErrorType type) {
        switch (type) {

            case EMPTY_NAME ->
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Name can not be empty.");

            case WRONG_FORMAT ->
                    JOptionPane.showMessageDialog(MainFrame.getInstance(),"Only numbers can be input.");

        }
    }

}
