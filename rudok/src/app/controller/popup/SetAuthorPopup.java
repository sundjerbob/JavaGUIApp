package app.controller.popup;

import app.factory.AbstractFactory;
import app.factory.ErrorType;
import app.factory.NameAlrExists;
import app.model.repository.Document;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SetAuthorPopup extends JDialog implements ActionListener {

    private JTextField text;
    private  TreeItem item;

    public SetAuthorPopup(){
        super(MainFrame.getInstance());
        setModal(true);
        Image icon = getImage("../images/authorLarge.png");
        setIconImage(icon);
        setSize(600,400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.cyan.darker());


        item = (TreeItem) MainFrame.getInstance().getITree().getTreeView().getLastSelectedPathComponent();

        JLabel label = new JLabel("Set author for this document:");
        label.setOpaque(false);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(mainPanel.getLocation().x + 200,mainPanel.getLocation().y + 100,200,25);


        text = new JTextField(((Document)item.getModel()).getAuthor());
        text.addActionListener(this);
        text.setOpaque(true);
        text.setBounds(mainPanel.getLocation().x + 250, mainPanel.getLocation().y + 135, 100,30 );

        text.setBackground(Color.cyan);

        mainPanel.add(label);
        mainPanel.add(text);

        setContentPane(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NameAlrExists d;
        if(text.getText() == ""){
            d = AbstractFactory.getInstance().createPopup(ErrorType.EMPTY_NAME);
            d.showError(this);
        }
        if(item.getModel() instanceof Document) {
            ((Document) item.getModel()).setAuthor(text.getText());
            dispose();
        }
    }


    private Image getImage(String fileName){
        URL imageURL = getClass().getResource(fileName);


        if(imageURL != null)
            return new ImageIcon(imageURL).getImage();

        System.out.println("klasa setAuthorPopup metoda getImage");
        return null;
    }


}
