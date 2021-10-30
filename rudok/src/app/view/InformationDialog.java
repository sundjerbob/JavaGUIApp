package app.view;



import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class InformationDialog extends JDialog {


    public InformationDialog(Image image){

        //setting parent component and title for JDialog
        super(MainFrame.getInstance(),"information");

        //setting icon of this window
        setIconImage(image);

        //this panel is holding content for the whole info window
        JPanel panel = new JPanel();
        panel.setBackground(Color.cyan);


        //isert image
        ImageIcon lableImage  = getImageIcon("moon.jpg");

        //setting size for this window
        setSize(lableImage.getIconWidth()+100,lableImage.getIconHeight()*3/2);


        //setting up label for text
        JLabel text = new JLabel("Tadija Simic 74/2019 RN",SwingConstants.CENTER);
        Dimension textDimension = new Dimension(lableImage.getIconWidth()+100,150);
        text.setPreferredSize(textDimension);


        setBackground(Color.black);

        //putting insert image in a label center
        JLabel label = new JLabel(lableImage,JLabel.CENTER);


        //adding labels to this panel(content holder)
        panel.add(text,BorderLayout.NORTH);
        panel.add(label,BorderLayout.CENTER);



        //setting location of this window
        setLocationRelativeTo(MainFrame.getInstance());

        //setting panel on the window
        setContentPane(panel);


        setVisible(true);


    }


    //getting image for Information dialog
    private ImageIcon getImageIcon(String path){
        URL url = getClass().getResource(path);
        if(url == null) {
            System.out.println("problem");
            return null;
        }
        return new ImageIcon(url);
    }
}
