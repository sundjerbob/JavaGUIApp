package app.view.repository;


import app.view.gui.Button;
import app.view.gui.MainFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditMode extends JPanel {

    private final DocumentView document;
    private final JPanel thumbnail;


    public EditMode (DocumentView documentView) {
        super(new BorderLayout());
        document = documentView;
        JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
        toolBar.add(new Button(MainFrame.getInstance().getActionManager().getChangeModeAction()));
        toolBar.setBorder(new EmptyBorder(10,5,0,5));
        add(toolBar,BorderLayout.EAST);
        toolBar.setBackground(new Color(0xC6F9F4));
        add(documentView.getPagesStack(),BorderLayout.CENTER);
        thumbnail = new JPanel(null);
        thumbnail.setBackground(new Color(0xC6F9F4));
        thumbnail.setPreferredSize(new Dimension(240,0));
        JScrollPane scrollPane = new JScrollPane(thumbnail);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.setVerticalScrollBar(new JScrollBar());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add( scrollPane ,BorderLayout.WEST);

    }


    public void updateThumbnail () {

        thumbnail.removeAll();
        thumbnail.setPreferredSize(new Dimension(240,230 * document.getPages().size() + 15 ));
        PageView curr;
        for(int i = 0; i < document.getPages().size(); i++){
            curr = document.getPages().get(i);
            curr.getPageThumbnail().setBounds(document.getLocation().x + 15, document.getLocation().y + i * 230 + 15,
                    200,200);
            thumbnail.add(curr.getPageThumbnail());
        }
        thumbnail.repaint();
    }
}

