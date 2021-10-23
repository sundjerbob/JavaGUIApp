package ui.view;

import javax.swing.*;
import javax.swing.text.Position;
import javax.xml.stream.Location;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private MainFrame(){
        init();
    }

    private void init(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension ss = kit.getScreenSize();
        setSize(ss.width/2, ss.height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Application Frame");
        setLocationRelativeTo(null);
    }

    public static MainFrame getInstance(){
        if(instance == null)
            return new MainFrame();
        return instance;
    }
}
