package app.view.repository;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


public class SlideshowMode extends JPanel  {

    private JPanel left;
    private JPanel right;
    private JLabel previousPage;
    private JLabel nextPage;
    private JPanel pagesStack;
    private DocumentView document;

    public SlideshowMode(DocumentView document){
        super(new BorderLayout());
        this.document = document;
        pagesStack = document.getPagesStack();
        left = new JPanel();
        right = new JPanel();

        previousPage = new JLabel();
        setBackground(Color.cyan.darker());
        previousPage.setOpaque(true);
        previousPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        previousPage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                goPrev();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(new Color(0x164040));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(Color.cyan.darker());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setBackground(new Color(0x032727));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setBackground(new Color(0x194242));
            }
        });

        nextPage = new JLabel();
        setBackground(Color.cyan.darker());
        nextPage.setOpaque(true);
        nextPage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nextPage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                goNext();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setBackground(new Color(0x164040));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                setBackground(Color.cyan.darker());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                setBackground(new Color(0x032727));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                setBackground(new Color(0x194242));
            }
        });




        left.add(previousPage);
        right.add(nextPage);

        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(pagesStack, BorderLayout.CENTER);


    }

    private void goPrev(){
        DocumentView document = (DocumentView)WorkspaceView.getCurrentlyOpened();

        int index = document.getPages().indexOf(document.getCurrentPage());

        if(index == -1){
            previousPage.setEnabled(false);
            nextPage.setEnabled(false);
        }

        if(index >= 1) {
            document.setCurrentPage(document.getPages().get(--index));
            ((CardLayout) pagesStack.getLayout()).show(pagesStack, document.getCurrentPage().getModel().getName());
        }
        if(index == 0)
            previousPage.setEnabled(false);
        else
            previousPage.setEnabled(true);
    }

    private void goNext(){

        int index = document.getPages().indexOf(document.getCurrentPage());

        if(index < document.getPages().size() - 1)
            document.setCurrentPage(document.getPages().get(++index));

        else if(index == document.getPages().size() - 1)
            nextPage.setEnabled(false);

        else
            nextPage.setEnabled(true);
    }

    private Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("SlideshowMode - load icon failed");
        return icon;
    }



}
