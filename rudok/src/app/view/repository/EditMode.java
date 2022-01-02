package app.view.repository;

import app.view.gui.DrawToolBar;
import javax.swing.*;
import java.awt.*;

public class EditMode extends JPanel {

    private final DocumentView document;
    private final JPanel thumbnail;
    private final JScrollPane scrollPane;


    public EditMode (DocumentView documentView, DrawToolBar toolBar) {

        super(new BorderLayout());
        document = documentView;

        add(toolBar,BorderLayout.EAST);
        add(documentView.getPagesStack(),BorderLayout.CENTER);

        thumbnail = new JPanel(null);
        thumbnail.setBackground(new Color(0xC6F9F4));
        thumbnail.setPreferredSize(new Dimension(240,0));
        scrollPane = new JScrollPane(thumbnail);
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
            curr.getPageThumbnail().setBounds(scrollPane.getLocation().x + 15, scrollPane.getLocation().y + i * 230 + 15,
                    200,200);
            thumbnail.add(curr.getPageThumbnail());
        }

        thumbnail.repaint();
    }
}

