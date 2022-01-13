package app.controller.popup;
import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.File;
import app.view.gui.Button;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;



public class SharePopup extends JDialog {

    private final JPanel scroll;
    private final List<CheckBox> boxes;
    private final TreeItem shared;


    public SharePopup(TreeItem newShared)  {
        shared = newShared;
        setSize(500, 500);
        setLocationRelativeTo(null);
        setModal(true);
        try {
            ImageIcon i = new ImageIcon(getClass().getResource("../actions/images/shareDoc.png"));
            setIconImage(i.getImage());
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        JPanel content = new JPanel(new BorderLayout());
        JLabel top = new JLabel("Make a copy of presentation: " + shared.getModel().getName() + " in other files.");
        top.setBackground(new Color(0xC6F9F4));
        top.setOpaque(true);
        top.setHorizontalAlignment(SwingConstants.CENTER);
        top.setPreferredSize(new Dimension(0,100));
        content.add(top,BorderLayout.NORTH);

        JPanel bot = new JPanel(null);
        bot.setPreferredSize(new Dimension(500,100));
        bot.setBackground(new Color(0xC6F9F4));
        content.add(bot,BorderLayout.SOUTH);

        JPanel mid = new JPanel(new BorderLayout());
        mid.setBorder(new EmptyBorder(0,100,0,100));
        mid.setBackground(new Color(0xC6F9F4));


        content.add(mid, BorderLayout.CENTER);

        Button cancel = new Button(null){
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        };
        cancel.setText("Cancel.");
        cancel.setBounds(100,35,80,30);
        bot.add(cancel);

        Button share = new Button(null) {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<File> result = new ArrayList();

                for( CheckBox curr : boxes){
                    if(curr.checked){
                        result.add(curr.file);
                    }

                }
                ((Document) shared.getModel()).setSharedWith(result);
                dispose();
            }
        };
        share.setText("Share.");
        share.setBounds(320,35,80,30);
        bot.add(share);

        /*************************************************************/

        List<NodeModel> allFiles = MainFrame.getInstance().getWorkspace().getModel().getChildren();
        scroll = new JPanel();
        BoxLayout box = new BoxLayout(scroll,BoxLayout.Y_AXIS);
        scroll.setLayout(box);
        boxes = new ArrayList<>();
        scroll.setBackground(Color.cyan.darker());




        scroll.setPreferredSize(new Dimension(300, 55 * (allFiles.size() - 1)));
        int height = 0;
        for (NodeModel curr : allFiles) {
            if( shared.getModel().getParent() == curr )
                continue;
            CheckBox checkBox = new CheckBox((File) curr);
            scroll.add(Box.createVerticalStrut(4));
            boxes.add(checkBox);
            scroll.add(Box.createVerticalStrut(4));
            //height += 5;
            //checkBox.setBounds(20,height,scroll.getWidth(),50);
            scroll.add(checkBox);
           // height += checkBox.getHeight();

        }
        scroll.repaint();


        JScrollPane scrollPane = new JScrollPane(scroll);
        scrollPane.setHorizontalScrollBar(null);
        mid.add(scrollPane,BorderLayout.CENTER);

        setContentPane(content);
        setVisible(true);
    }

    class CheckBox extends JCheckBox implements ItemListener {

        private boolean checked;
        private File file;

        public CheckBox(File file) {

            super(file.getName());
            System.out.println("CEK BOX");
            this.file = file;
            setFocusPainted(false);
            setBorder(new EmptyBorder(3,2,3,2));

            if( ((Document) shared.getModel()).getSharedWith() != null && !((Document) shared.getModel()).getSharedWith().isEmpty() ) {
                    if (((Document) shared.getModel()).getSharedWith().contains(this.file)) {
                        checked = true;
                        setSelected(true);

                    }
            }

            try {
               //setIcon(new ImageIcon( getClass().getResource("images/fileOpen1.png")));
            } catch ( Exception e ) {
                e.printStackTrace();
            }

            addItemListener(this);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            checked = e.getStateChange() == ItemEvent.SELECTED;
        }

        @Override
        public void paint(Graphics g) {
            if(checked)
                setBackground(Color.cyan);
            else
                setBackground(Color.cyan.darker());
            super.paint(g);
        }
    }
}