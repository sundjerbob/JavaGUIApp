package app.view.repository;


import app.model.repository.Document;
import app.model.repository.Page;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;
import app.view.state.EditState;
import app.view.state.StateManager;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
public class DocumentView extends JPanel implements ISubscriber {

    public static final String SLIDE_SHOW = "2";
    public static final String  EDIT_MODE = "1";
    public static final String BLANK = "0";


    private final Document model;
    private final FileView parentView;
    private final StateManager stateManager;
    private final JLabel author;
    private final JPanel pagesStack;
    private final JPanel documentContent;
    private final JLabel slideLabel;
    private final SlideshowMode slideshowMode;
    private final EditMode editMode;
    private ArrayList<PageView> pages;
    private int currentPage;



    public DocumentView(Document model,FileView parentView){
        super(new BorderLayout());
        this.model = model;
        this.parentView = parentView;
        model.addSubscriber(this);
        currentPage = -1;//document is made with no pages so, we set current to -1
        stateManager = new StateManager(this);
        pagesStack = new JPanel(new CardLayout());
        setBackground(Color.cyan.darker());

        author = new JLabel(loadIcon("images/userLabel.png"));
        author.setOpaque(false);
        author.setPreferredSize(new Dimension(0,20));
        author.setBorder(new EmptyBorder(0,0,0,10));
        author.setHorizontalAlignment(SwingConstants.RIGHT);
        author.setVerticalAlignment(SwingConstants.CENTER);
        add(author, BorderLayout.NORTH);

        slideLabel = new JLabel();
        slideLabel.setHorizontalAlignment(SwingConstants.CENTER);
        slideLabel.setVerticalAlignment(SwingConstants.CENTER);
        slideLabel.setOpaque(true);
        slideLabel.setFont(new Font(slideLabel.getFont().getName(),Font.ITALIC,20));
        slideLabel.setPreferredSize(new Dimension(0,20));
        slideLabel.setBackground(Color.cyan.darker());
        add(slideLabel, BorderLayout.SOUTH);

        documentContent = new JPanel(new CardLayout());
        add(documentContent, BorderLayout.CENTER);

        JPanel blank = new JPanel(new BorderLayout());
        blank.setBackground(Color.cyan.darker());
        JLabel blankLabel = new JLabel("Add first page!",loadIcon("images/firstPage.png"), SwingConstants.CENTER);
        blankLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = MainFrame.getInstance().getActionManager().getNewAction().getName(model, "Slide");
                model.addChild(new Page(name, model));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                blankLabel.setBackground(Color.cyan);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                blankLabel.setBackground(Color.lightGray);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                blankLabel.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                blankLabel.setBackground(Color.cyan.darker());

                updateUI();

            }
        });
        blankLabel.setBackground(Color.CYAN.darker());
        blankLabel.setOpaque(true);
        blankLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        blank.add(blankLabel, BorderLayout.CENTER);


        editMode = new EditMode(this);
        slideshowMode = new SlideshowMode(this);

        documentContent.add(editMode,EDIT_MODE);//mode 1(default mode)
        documentContent.add(slideshowMode,SLIDE_SHOW);//mode 2
        documentContent.add(blank,BLANK);//means u have no pages to work with (gives u an option to make new one)
        setModeState(BLANK);//when u make new document it has no pages
    }



    public void setCurrentPage(int  currentPageIndex) {
        if(currentPageIndex == currentPage)
            return;
        if(!pages.isEmpty() && currentPage > -1 )
            pages.get(currentPage).setSel(false);
        else
            setModeState(BLANK);

        if(currentPageIndex != -1) {
            PageView curr = pages.get(currentPageIndex);
            curr.setSel(true);
            ((CardLayout) pagesStack.getLayout()).show(pagesStack, curr.getModel().getName());//curr page on top
            slideLabel.setText(curr.getModel().getName());
            stateManager.getCurrModeState().set();//if we have pages to work with we set current state panel
        }
        currentPage = currentPageIndex;
        slideshowMode.updateArrows();
        editMode.updateThumbnail();
    }


    public void removePage(PageView page){
        int index = pages.indexOf(page);
        pages.remove(page);
        pagesStack.remove(page);

        if(pages.isEmpty()) {
            setCurrentPage(-1);
        }
        else if(currentPage == index){
            if(index < pages.size()) {
                setCurrentPage(index);

            } else {
                setCurrentPage(index - 1);
            }
        }
        editMode.updateThumbnail();
        slideshowMode.updateArrows();
    }

    public void switchMode() {
        if(stateManager.getCurrModeState() instanceof EditState) {
            stateManager.setSlideshowState();
        }
        else
            stateManager.setEditState();
    }

    // This method is used to toggle between 2 ways of viewing documents content
    public void setModeState(String s) {
        if(s.equals(SLIDE_SHOW) )
            slideshowMode.setPageStack(pagesStack);
        else if( s.equals(EDIT_MODE) )
            editMode.add(pagesStack,BorderLayout.CENTER);
        ((CardLayout) documentContent.getLayout()).show(documentContent, s);
        if(s.equals(BLANK))
            slideLabel.setText("");

    }

    @Override
    public void update( Notification notification ) {

        JPanel curr = WorkspaceView.getCurrentlyOpened();

        if( notification.getType() == NotificationType.DOUBLE_CLICK ) {
            if( curr != this )
                parentView.getParentView().display(this);
        }

        else if( notification.getType() == NotificationType.ADD_ACTION ) {
            if( pages == null ){
                pages = new ArrayList<>();
            }
            PageView newPage = new PageView((Page) notification.getNotificationObject(), this);
            pages.add(newPage);//added in array so, we have access to it with its index
            pagesStack.add(newPage.getModel().getName(),newPage);
            if( pages.size() == 1 ){
                stateManager.getCurrModeState().set();
                setCurrentPage(pages.indexOf(newPage));
            }

            editMode.updateThumbnail();
            slideshowMode.updateArrows();
        }

        else if(notification.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeDocument(this);
            if( curr == this ||
                    curr == parentView)
                parentView.display();
        }

        else if(notification.getType() == NotificationType.RENAME_ACTION){
            if( curr == parentView)
                parentView.display();
            else if( curr == this )
                parentView.getParentView().display(this);
        }

        else if(notification.getType() == NotificationType.AUTHOR_SET){
            author.setText(model.getAuthor());

            if(curr == this)
                repaint();
        }
        else if(notification.getType() == NotificationType.THEME_SET){

            if(!pages.isEmpty())
                for(PageView pageView : pages){
                pageView.setBackgroundTheme();
                }
        }
    }




    private Icon loadIcon(String fileName){
        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if(imageURL != null)
            icon = new ImageIcon(imageURL);
        else
            System.out.println("DocumentView - load icon failed");
        return icon;
    }





    public Document getModel() {
        return model;
    }

    public FileView getParentView() {
        return parentView;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public ArrayList<PageView> getPages(){
        return pages;
    }

    public JPanel getPagesStack() {
        return pagesStack;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

}
