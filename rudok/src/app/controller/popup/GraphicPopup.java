package app.controller.popup;

import app.model.repository.Page;
import app.view.gui.Button;
import app.view.gui.MainFrame;
import app.view.repository.DocumentView;
import app.view.repository.PageView;
import app.view.repository.SlotView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.net.URL;

public class GraphicPopup extends JDialog {


    private final JPanel inColPanel;
    private final JPanel outColPanel;
    private final JPanel preview;
    private final JTextField strokeField;
    private int stroke ;
    private Color insideColor ;
    private Color outLineColor;




    public GraphicPopup(DocumentView doc){
        super(MainFrame.getInstance());
        setSize(400,800);
        setLocationRelativeTo(null);
        setModal(true);
        URL imageURL = getClass().getResource("../actions/images/color-picker.png");
        ImageIcon icon;
        if(imageURL != null){
            icon = new ImageIcon(imageURL);
            setIconImage(icon.getImage());
        }
        else
            System.out.println("color icon failed");

        /***************************************************************************/

        PageView page = doc.getPages().get(doc.getCurrentPage());

        if(page.getSelectedSlot() != null){
            SlotView selectedSlot = page.getSelectedSlot();
             stroke = selectedSlot.getModel().getStroke();
             outLineColor = selectedSlot.getModel().getOutLineColor();
             insideColor = selectedSlot.getModel().getInsideColor();
        }
        else if(page.getSlots() != null && page.getSlots().size() > 0) {
            SlotView anySlot = page.getSlots().get(0);
            stroke = anySlot.getModel().getStroke();
            insideColor = anySlot.getModel().getInsideColor();
            outLineColor = anySlot.getModel().getOutLineColor();
        }
        else if(page.getSlots() == null){
            insideColor = Color.black;
            outLineColor = Color.yellow;
            stroke = 5;
        }
         preview = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                GeneralPath shape = new GeneralPath();
                shape.moveTo(getWidth() / 4,getWidth()/ 4);
                shape.lineTo((getWidth() / 4) * 3,getWidth() / 4);
                shape.lineTo((getWidth() / 4) * 3,(getWidth() / 4) * 3);
                shape.lineTo(getWidth() / 4,(getWidth() / 4) * 3);
                shape.closePath();

                BasicStroke path = new BasicStroke(stroke);

                ((Graphics2D) g).setStroke(path);
                g.setColor(outLineColor);
                ((Graphics2D) g).draw(shape);
                g.setColor(insideColor);
                ((Graphics2D) g).fill(shape);

                g.setColor(Color.cyan);
                ((Graphics2D) g).setStroke(new BasicStroke(8));
                g.drawRect(0,0,preview.getWidth(),preview.getHeight());

            }
        };
        preview.setBackground(Color.white);

        inColPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                ((Graphics2D)g).setStroke(new BasicStroke(3));
                g.drawRect(0,0,inColPanel.getWidth(),inColPanel.getHeight());
            }
        };
        inColPanel.setBackground(insideColor);
        inColPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(GraphicPopup.this,"Select a color",insideColor);
                insideColor = newColor;
                inColPanel.setBackground(newColor);
            }
        });


        outColPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                ((Graphics2D)g).setStroke(new BasicStroke(3));
                g.drawRect(0,0,outColPanel.getWidth(),outColPanel.getHeight());
            }
        };
        outColPanel.setBackground(outLineColor);
        outColPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(GraphicPopup.this,"Select a color",outLineColor);
                outLineColor = newColor;
                outColPanel.setBackground(newColor);
            }
        });

        strokeField = new JTextField();

        JLabel inColLabel = new JLabel("Set fill color.");
        JLabel outColLabel = new JLabel("Set outline color.");
        JLabel strokeLabel = new JLabel("Set outline width.");

        Button apply = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!strokeField.getText().equals(""))
                    stroke =Integer.parseInt(strokeField.getText());
                preview.repaint();
            }
        };
        Button cancel = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        };
        Button ok = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (page.getSelectedSlot() == null)
                    ((Page) page.getModel()).setSlotSettings(insideColor,outLineColor, stroke,null);
                else
                    ((Page) page.getModel()).setSlotSettings(insideColor,outLineColor,stroke,
                            page.getSelectedSlot().getModel());
                dispose();
            }
        };
        apply.setText("Apply");
        cancel.setText("Cancel");
        ok.setText("Ok");


        /*****************************layout*******************************/

        preview.setBounds(100,100, 200, 200);
        inColLabel.setBounds(100,350, 150,30);
        inColPanel.setBounds(270,350,30,30);
        outColLabel.setBounds(100,410, 150, 30);
        outColPanel.setBounds(270,410,30,30);
        strokeLabel.setBounds(100,470, 150,30);
        strokeField.setBounds(250 ,470,50,30);
        cancel.setBounds(50,550, 100,30);
        apply.setBounds(200,550,70,30);
        ok.setBounds(300,550,70,30);

        JPanel content = new JPanel(null);
        content.setBackground(new Color(210,220,230));

        content.add(preview);
        content.add(inColPanel);
        content.add(strokeField);
        content.add(inColLabel);
        content.add(outColPanel);
        content.add(outColLabel);
        content.add(strokeLabel);
        content.add(cancel);
        content.add(apply);
        content.add(ok);

        setContentPane(content);
        setVisible(true);

    }



}
