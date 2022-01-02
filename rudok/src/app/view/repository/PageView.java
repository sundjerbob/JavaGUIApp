package app.view.repository;

import app.model.node.NodeModel;
import app.model.repository.Document;
import app.model.repository.Page;
import app.model.repository.Slot;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.state.EditState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;


public class PageView extends JPanel implements ISubscriber {


    private final Page model;
    private final DocumentView parentView;
    private final PageFramework pageFramework;
    private final PageThumbnail pageThumbnail;
    private Image image;
    private ArrayList <SlotView> slots;
    private SlotView selectedSlot;


    public PageView (Page model, DocumentView parent) {
        super( new GridBagLayout() );

        this.model = model;
        model.addSubscriber(this);
        parentView = parent;
        setBackground(new Color(210,220,230));


        pageFramework = new PageFramework();
        add(pageFramework);

        setBackgroundTheme();
        pageThumbnail = new PageThumbnail();
    }


    public void setSel (boolean arg){
        if(arg)
            pageThumbnail.setBackground(Color.cyan);
        else
            pageThumbnail.setBackground(new Color(210,220,230));
    }

    public void selectSlot(SlotView selected){
        if(selected == selectedSlot)
            return;
        if(selectedSlot != null)
            selectedSlot.setSelected(false);
        if(selected != null){
            selected.setSelected(true);
            slots.remove(selected);
            slots.add(selected);
        }
        selectedSlot = selected;
        pageFramework.repaint();
        pageThumbnail.label.repaint();
    }

    public void setBackgroundTheme () {
        image = ((Document) getModel().getParent()).getTheme();

        if(pageThumbnail != null && pageFramework != null) {
            pageThumbnail.repaint();
            pageFramework.repaint();
        }
    }


    @Override
    public void update (Notification notification) {

        if(notification.getType() == NotificationType.DOUBLE_CLICK) {
            if(WorkspaceView.getCurrentlyOpened() != parentView)
                parentView.getParentView().getParentView().display(parentView);

            if(parentView.getPages().get(parentView.getCurrentPage() )!= this)
                parentView.setCurrentPage(parentView.getPages().indexOf(this));
        }

        else if (notification.getType() == NotificationType.REMOVE_ACTION) {
           parentView.removePage(this);
        }

        else if(notification.getType() == NotificationType.SLOT_ADDED) {

            if(slots == null )
                slots = new ArrayList<>();

            slots.add(new SlotView((Slot) notification.getNotificationObject(),this));
            pageFramework.repaint();
            pageThumbnail.label.repaint();
        }

        else if(notification.getType() == NotificationType.SLOT_RELOCATED ||
                notification.getType() == NotificationType.SLOT_CHANGED) {

            pageFramework.repaint();
            pageThumbnail.repaint();
        }

    }



    public NodeModel getModel () {
        return model;
    }

    public PageThumbnail getPageThumbnail () {
        return pageThumbnail;
    }

    public PageFramework getPageFramework() {
        return pageFramework;
    }

    public ArrayList<SlotView> getSlots() {
        return slots;
    }

    public SlotView getSelectedSlot() {
        return selectedSlot;
    }



    public class PageFramework extends JPanel{

        private PageFramework(){

            setPreferredSize(new Dimension(800,800));
            setMinimumSize(new Dimension(600,600));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (parentView.getStateManager().getCurrModeState() instanceof EditState)
                        parentView.getStateManager().getCurrDrawState().mousePressed(e);

                }
            });
            addMouseMotionListener(new MouseMotionAdapter(){

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (parentView.getStateManager().getCurrModeState() instanceof EditState)
                    parentView.getStateManager().getCurrDrawState().mouseDragged(e);
                }


            });
        }



        @Override
        public void paintComponent(Graphics g) {

            if (image != null)
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            else {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            if (slots != null){
            for( SlotView curr : slots)
                    curr.paint(g,this);
            }




            g.setColor(Color.cyan.darker());
            ((Graphics2D) g).setStroke(new BasicStroke(8));
            g.drawRect(0, 0, getWidth(), getHeight());

        }

        public PageView getPage(){
            return PageView.this;
        }

    }



     class PageThumbnail extends JPanel {

         public final JPanel label;


         public PageThumbnail() {
             super(new BorderLayout());
             setBackground(new Color(210, 220, 230));

             JLabel name = new JLabel( model.getName(), SwingConstants.CENTER);
             name.setVerticalAlignment(JLabel.CENTER);
             name.setPreferredSize(new Dimension(0, 30));
             name.setFont(new Font(getFont().getName(), Font.BOLD, 14));
             name.setBackground(Color.cyan.darker());
             name.setOpaque(true);
             add(name, BorderLayout.SOUTH);

             label = new JPanel() {
                 @Override
                 public void paintComponent(Graphics g) {

                     if (image != null)
                         g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                     else {
                         g.setColor(Color.WHITE);
                         g.fillRect(0, 0, getWidth(), getHeight());
                     }
                     if (slots != null) {
                         for (SlotView curr : slots){
                             curr.paint(g,PageThumbnail.this);
                         }
                     }

                     g.setColor(Color.cyan.darker());
                     ((Graphics2D) g).setStroke(new BasicStroke(8));
                     g.drawRect(0, 0, getWidth(), getHeight());


                 }
             };
             label.setPreferredSize(new Dimension(140, 140));
             JPanel cont = new JPanel();
             cont.setOpaque(false);
             cont.setBorder(new EmptyBorder(10, 30, 20, 30));
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
}



