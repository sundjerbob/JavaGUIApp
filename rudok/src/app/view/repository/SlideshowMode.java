package app.view.repository;




import app.view.gui.Button;
import app.view.gui.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


public class SlideshowMode extends JPanel {

    private final DocumentView document;
    private final JPanel content;
    private final JLabel previousPage;
    private final JLabel nextPage;

    public SlideshowMode(DocumentView document) {
        super(new BorderLayout());
        this.document = document;

        JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
        content = new JPanel(new BorderLayout());
        toolBar.add(new Button(MainFrame.getInstance().getActionManager().getChangeModeAction()));
        add(toolBar,BorderLayout.EAST);
        toolBar.setBorder(new EmptyBorder(10,5,0,5));
        toolBar.setBackground(new Color(0xC6F9F4));
        add(content,BorderLayout.CENTER);

        previousPage = new JLabel(loadIcon("images/prev.png"),SwingConstants.CENTER);
        previousPage.setOpaque(true);
        previousPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        previousPage.setForeground(Color.BLACK);
        previousPage.setPreferredSize(new Dimension(100,100));
        previousPage.setBackground(Color.cyan.darker());
        previousPage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(document.getCurrentPage() > 0)
                    document.setCurrentPage(document.getCurrentPage() - 1);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                previousPage.setBackground(new Color(0x528B8B));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                previousPage.setBackground(Color.cyan.darker());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                previousPage.setBackground(Color.gray);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                previousPage.setBackground(new Color(0x528B8B));
            }
        });

        nextPage = new JLabel(loadIcon("images/next.png"),SwingConstants.CENTER);
        nextPage.setOpaque(true);
        nextPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextPage.setPreferredSize(new Dimension(100,100));
        nextPage.setBackground(Color.cyan.darker());
        nextPage.setForeground(Color.BLACK);
        nextPage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(document.getCurrentPage() < document.getPages().size() - 1)
                    document.setCurrentPage(document.getCurrentPage() + 1);
                else
                    System.out.println("go prev????????????????");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                nextPage.setBackground(new Color(0x528B8B));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                nextPage.setBackground(Color.cyan.darker());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                nextPage.setBackground(Color.gray);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                nextPage.setBackground(new Color(0x528B8B));

            }
        });

        content.add(nextPage, BorderLayout.EAST);
        content.add(previousPage, BorderLayout.WEST);
        add(content,BorderLayout.CENTER);
    }

    public void updateArrows(){
        int index = document.getCurrentPage();
        if(document.getPages().size() == 1) {
            previousPage.setVisible(false);
            nextPage.setVisible(false);
        }
        else if(index > 0 && index < document.getPages().size() - 1){
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        }
        else if(index == 0){
            nextPage.setVisible(true);
            previousPage.setVisible(false);
        }
        else if(index  ==  document.getPages().size() - 1){
             nextPage.setVisible(false);
             previousPage.setVisible(true);
        }
    }

    public void setPageStack(JPanel pageStack){
        content.add(pageStack,BorderLayout.CENTER);
    }

    private Icon loadIcon(String fileName) {
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("SlideshowMode - load icon failed");
        return icon;
    }


}
