package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.Page;
import app.model.repository.Slot;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class PageView extends JPanel implements ISubscriber {


    private final Page model;
    private final DocumentView parentView;
    private final JPanel content;
    private final PageThumbnail pageThumbnail;
    private Image image;
    private ArrayList<SlotView> slots;


    public PageView (Page model, DocumentView parent) {
        super(new GridBagLayout());

        this.model = model;
        model.addSubscriber(this);
        parentView = parent;

        content = new JPanel() {
            @Override
            public void paintComponent (Graphics g) {
                super.paintComponent(g);
                if (image != null)
                    g.drawImage(image, 0, 0, getWidth(),getHeight(), null);
                else {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                Graphics2D g2 = (Graphics2D) g;
                if(slots != null)
                for(SlotView curr : slots ){
                    curr.paint(g);
                }

            }
        };
        content.setPreferredSize(new Dimension(800,800));
        content.setMinimumSize(new Dimension(600,600));
        add(content);
        content.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(parent.getStateManager().getCurrDrawState() != null)
                    parent.getStateManager().getCurrDrawState().mouseClicked(e);
            }
        });

        setBackgroundTheme();
        setBackground(new Color(210,220,230));

        pageThumbnail = new PageThumbnail();
    }

    public void setSel (boolean arg){
        if(arg)
           pageThumbnail.setBackground(Color.cyan);
        else
            setBackground(new Color(210,220,230));
    }

    class PageThumbnail extends JPanel {



        public PageThumbnail () {
            super(new BorderLayout());
            setBackground(new Color(210,220,230));

            JLabel name = new JLabel(model.getName(),SwingConstants.CENTER);
            name.setVerticalAlignment(JLabel.CENTER);
            name.setPreferredSize(new Dimension(0, 30));
            name.setFont(new Font(getFont().getName(),Font.BOLD,14));
            name.setBackground(Color.cyan.darker());
            name.setOpaque(true);
            add(name,BorderLayout.SOUTH);

            JPanel label = new JPanel(){
                @Override
                public void paint (Graphics g) {
                    super.paint(g);
                    g.setColor(Color.black);
                    g.drawRect( 0, 0,getWidth() - 1, getHeight() - 1);
                    if(image != null)
                        g.drawImage(image,0,0,getWidth(),getHeight(),null);
                    else{
                        g.setColor(Color.WHITE);
                        g.fillRect(0, 0, getWidth(), getHeight());
                    }
                }
            };
            label.setPreferredSize(new Dimension(140, 140));
            JPanel cont = new JPanel();
            cont.setOpaque(false);
            cont.setBorder(new EmptyBorder(10, 31, 20, 29));
            cont.add(label);
            add(cont, BorderLayout.CENTER);

            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseReleased(MouseEvent e) {

                    name.setBackground(new Color(0x528B8B));
                    parentView.setCurrentPage(parentView.getPages().indexOf(PageView.this));

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    name.setBackground(Color.cyan);
                }


                @Override
                public void mouseEntered(MouseEvent e) {
                    name.setBackground(new Color(0x528B8B));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    name.setBackground(Color.cyan.darker());
                }
            });
        }
    }






    @Override
    public void update (Notification notification) {

        if(notification.getType() == NotificationType.DOUBLE_CLICK){
            int index = parentView.getCurrentPage();

            if(WorkspaceView.getCurrentlyOpened() != parentView)
                parentView.getParentView().getParentView().display(parentView);

            if(parentView.getCurrentPage() != index)
                parentView.setCurrentPage(index);
        }

        else if (notification.getType() == NotificationType.REMOVE_ACTION){
           parentView.removePage(this);
        }

        else if(notification.getType() == NotificationType.SLOT_ADDED){
            if(slots == null )
                slots = new ArrayList<>();
            slots.add(new SlotView((Slot) notification.getNotificationObject()));
            content.repaint();
        }
        else if(notification.getType() == NotificationType.SLOT_REMOVED)
        {
            slots.remove(slots.get((int) notification.getNotificationObject()));
        }
    }

    public void setBackgroundTheme () {
        image = ((Document) getModel().getParent()).getTheme();

        if(pageThumbnail != null && content != null) {
            pageThumbnail.repaint();
            content.repaint();
        }
    }

    public NodeModel getModel () {
        return model;
    }

    public PageThumbnail getPageThumbnail () {
        return pageThumbnail;
    }

    public JPanel getContent() {
        return content;
    }
}
