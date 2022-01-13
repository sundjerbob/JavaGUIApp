package app;

import app.view.gui.MainFrame;

public class Main {

    public static void main ( String[] args ) {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.init();
        mainFrame.setVisible(true);
    }
}
