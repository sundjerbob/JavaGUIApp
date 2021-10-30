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
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.cyan);


        //isert image
        ImageIcon lableImage  = getImageIcon("sunce.png");

        //setting size for this window
        setSize(lableImage.getIconWidth()+100,lableImage.getIconHeight()+250);

        Dimension textDimension = new Dimension(lableImage.getIconWidth()+100,150);

        //setting up label for text
        JLabel text = new JLabel("Tadija Simic 74/2019 RN",SwingConstants.CENTER);
        text.setOpaque(true);
        text.setBackground(Color.CYAN.darker());
        text.setPreferredSize(textDimension);


        //putting insert image in a label center
        JLabel label = new JLabel(lableImage);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);




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
