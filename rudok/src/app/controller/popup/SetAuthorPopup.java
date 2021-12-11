package app.controller.popup;

import app.factory.AbstractFactory;
import app.factory.ErrorType;
import app.factory.NameAlrExists;
import app.model.repository.Document;
import app.view.gui.MainFrame;
import app.view.repository.DocumentView;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SetAuthorPopup extends JDialog implements ActionListener {

    private JTextField text;
    private  TreeItem item;
    private DocumentView document;

    public SetAuthorPopup(DocumentView document){
        super(MainFrame.getInstance());
        setTitle("Set document author window");
        setModal(true);
        Image icon = getImage("../actions/images/authorLarge.png");
        setIconImage(icon);
        setSize(400,200);
        setLocationRelativeTo(null);
        this.document = document;


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.cyan.darker());



        JLabel label = new JLabel("Set author for this document:");
        label.setOpaque(false);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(mainPanel.getLocation().x + 100,mainPanel.getLocation().y + 50,200,25);

        text = new JTextField();

        if(this.document == null){
           item = MainFrame.getInstance().getITree().getSelectedTreeItem();
           text.setText(((Document)item.getModel()).getAuthor());
        }
        text.addActionListener(this);
        text.setOpaque(true);
        text.setBounds(mainPanel.getLocation().x +  150, mainPanel.getLocation().y + 80, 100,25 );
        text.setBackground(Color.cyan);

        mainPanel.add(label);
        mainPanel.add(text);

        setContentPane(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NameAlrExists d;
        String str = text.getText();
        if(str == ""){
            d = AbstractFactory.getInstance().createPopup(ErrorType.EMPTY_NAME);
            d.showError(this);
        }

        else if(document != null)
            document.getModel().setAuthor(str);

        else if(item.getModel() instanceof Document)
                ((Document) item.getModel()).setAuthor(str);

        dispose();

    }


    private Image getImage(String fileName){
        URL imageURL = getClass().getResource(fileName);

        if(imageURL != null)
            return new ImageIcon(imageURL).getImage();

        System.out.println("klasa setAuthorPopup metoda getImage");
        return null;
    }


}
