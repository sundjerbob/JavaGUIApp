package app;

import app.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
}
