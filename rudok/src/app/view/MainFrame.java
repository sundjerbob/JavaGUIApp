package app.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    MainFrame(){
        init();
    }

    public static MainFrame getInstance(){
        if(instance == null)
            instance = new MainFrame();
        return instance;
    }

    private void init(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        d.width /= 2;
        d.height /= 2;
        setSize(d);
        setLocationRelativeTo(null);
        JScrollPane left = new JScrollPane();
        JPanel right = new JPanel();
        left.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        JSplitPane mainPanel = new JSplitPane(1,left,right);
        mainPanel.setDividerLocation(d.width/4);
        add(mainPanel);


    }

}

