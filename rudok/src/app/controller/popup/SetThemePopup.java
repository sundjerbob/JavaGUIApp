package app.controller.popup;

import app.model.repository.Document;
import app.view.gui.Button;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SetThemePopup extends JDialog {


    public SetThemePopup(){
        super(MainFrame.getInstance());
        setTitle("Set document theme window");

        setSize(500,250);
        setLocationRelativeTo(getParent());
        setModal(true);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Choose theme...",SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(0,100));

        JPanel center = new JPanel();
        BoxLayout box = new BoxLayout(center, BoxLayout.X_AXIS);
        center.setLayout(box);
        center.setBorder(new EmptyBorder(40,20,40,20));

        TreeItem item = MainFrame.getInstance().getITree().getSelectedTreeItem();

        Button spring = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(item.getModel() instanceof Document){
                    ((Document)item.getModel()).setTheme(1);
                    dispose();
                }
                else
                    System.out.println("problemmmm");
            }
        };
        spring.setText("Spring");
        center.add(spring);

        center.add(Box.createHorizontalStrut(10));

        Button summer = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(item.getModel() instanceof Document){
                    ((Document)item.getModel()).setTheme(2);
                    dispose();
                }
                else
                    System.out.println("problemmmm");
            }
        };
        summer.setText("Summer");
        center.add(summer);

        center.add(Box.createHorizontalStrut(10));

        Button fall = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(item.getModel() instanceof Document){
                    ((Document)item.getModel()).setTheme(3);
                    dispose();
                }
                else
                    System.out.println("problemmmm");
            }
        };
        fall.setText("Fall");
        center.add(fall);

        center.add(Box.createHorizontalStrut(10));

        Button winter = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(item.getModel() instanceof Document){
                    ((Document)item.getModel()).setTheme(4);
                    dispose();
                }
                else
                    System.out.println("problemmmm");
            }
        };
        winter.setText("Winter");
        center.add(winter);

        center.add(Box.createHorizontalStrut(10));

        Button defaultTheme = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(item.getModel() instanceof Document){
                    ((Document)item.getModel()).setTheme(0);
                    dispose();
                }
                else
                    System.out.println("problemmmm");
            }
        };
        defaultTheme.setText("Default");
        center.add(defaultTheme);

        mainPanel.add(center,BorderLayout.CENTER);
        setContentPane(mainPanel);
        setVisible(true);
    }




}
