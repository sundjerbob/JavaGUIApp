package app.controller.popup;

import app.controller.command.RenameCommand;
import app.controller.errorHandler.ErrorHandler;
import app.controller.errorHandler.ErrorType;
import app.model.node.NodeComposite;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class RenamePopup extends JDialog implements ActionListener{

    JTextField text;
    TreeItem item;


    public RenamePopup(TreeItem item){
        super(MainFrame.getInstance(),"New name.",true);
        this.item = item;

        setSize(600,400);
        setLocationRelativeTo(null);
        setIconImage(getImage("images/editing.png"));


        //mainPanel the content holder
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.cyan.darker());



        JLabel label = new JLabel("Change name to:");
        label.setOpaque(false);

        label.setHorizontalAlignment(JLabel.CENTER);



        text = new JTextField(item.getModel().getName());
        text.addActionListener(this);
        text.setOpaque(true);
        text.setBounds(mainPanel.getLocation().x + 250, mainPanel.getLocation().y + 135, 100,30 );
        text.setBackground(Color.cyan);
        mainPanel.add(text);

        label.setBounds(mainPanel.getLocation().x + 200,mainPanel.getLocation().y + 100,200,25);
        mainPanel.add(label);

        JButton renameButton = new JButton("Rename");
        renameButton.setForeground(Color.CYAN);
        renameButton.setBackground(new Color(0x528B8B));
        //renameButton.setBorder(new EmptyBorder(0, 20, 0, 20));
        renameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(text.getText().equals(""))
                    ErrorHandler.getInstance().createPopup(ErrorType.EMPTY_NAME);


                MainFrame.getInstance().getCommandManager().addCommand(new RenameCommand(text.getText(), (NodeComposite) item.getModel()));
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.CYAN);
        cancelButton.setBackground(new Color(0x528B8B));

        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });

        renameButton.setBounds(mainPanel.getLocation().x + 150, mainPanel.getLocation().y + 250, 100, 50);
        cancelButton.setBounds(mainPanel.getLocation().x + 350,mainPanel.getLocation().y + 250, 100, 50);

        mainPanel.add(renameButton);
        mainPanel.add(cancelButton);

        setContentPane(mainPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String t = text.getText();
        System.out.println(t);
        if(t.equals("")) {
            ErrorHandler.getInstance().createPopup(ErrorType.EMPTY_NAME);
        }
        else {
        item.getModel().setName(text.getText());
        MainFrame.getInstance().getITree().getTreeView().updateUI();
        dispose();
        }
    }

    private Image getImage(String fileName){
        URL imageURL = getClass().getResource(fileName);


        if(imageURL != null)
            return new ImageIcon(imageURL).getImage();

        System.out.println("klasa RenamePopup metoda getImage");
        return null;
    }
}
