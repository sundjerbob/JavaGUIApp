package app.view.repository;

import app.model.node.NodeComposite;
import app.model.repository.Document;
import app.model.repository.Page;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;
import app.view.state.StateManager;


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
public class DocumentView extends JPanel implements ISubscriber {

    private static final String SLIDE_SHOW = "2";
    private static final String  EDIT_MODE = "1";
    private static final String BLANK = "0";


    private Document model;
    private FileView parentView;
    private ArrayList<PageView> pages;
    private PageView currentPage;
    private StateManager stateManager;
    private JLabel author;
    private JPanel pagesStack;
    private JPanel content;


    public DocumentView(Document model,FileView parentView){
        super(new BorderLayout());
        this.model = model;
        this.parentView = parentView;
        model.addSubscriber(this);

        setBackground(Color.cyan.darker());

        author = new JLabel((ImageIcon)loadIcon("images/userLabel.png"));
        author.setHorizontalAlignment(SwingConstants.CENTER);
        author.setPreferredSize(new Dimension(0,20));
        author.setBorder(new EmptyBorder(0,0,0,10));
        author.setHorizontalAlignment(SwingConstants.RIGHT);
        author.setVerticalAlignment(SwingConstants.CENTER);
        author.setBackground(Color.cyan.darker());
        author.setOpaque(true);
        add(author,BorderLayout.NORTH);

        stateManager = new StateManager();

        pagesStack = new JPanel(new CardLayout());

        content = new JPanel(new CardLayout());
        add(content, BorderLayout.CENTER);

        SlideshowMode slideshowMode = new SlideshowMode(this);
        EditMode editMode = new EditMode(this);
        JPanel blank = new JPanel(new BorderLayout());
        blank.setBackground(Color.cyan.darker());

        JLabel blankLabel = new JLabel("Add first page!",loadIcon("images/firstPage.png"),SwingConstants.CENTER);

        blankLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String name = MainFrame.getInstance().getActionManager().getNewAction().getName((NodeComposite) model,
                        "Slide");
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


        content.add(SLIDE_SHOW, slideshowMode);
        content.add(EDIT_MODE, editMode);
        content.add(BLANK, blank);

        ((CardLayout)content.getLayout()).show(content, BLANK);
    }

    public void setModeState(String s){
        switch (s){
            case SLIDE_SHOW -> {
                ((CardLayout) content.getLayout()).show(content, SLIDE_SHOW);
                stateManager.setSlideshowState();
            }

            case EDIT_MODE-> {
                ((CardLayout) content.getLayout()).show(content, EDIT_MODE);
                stateManager.setSlideshowState();
            }
            case BLANK -> ((CardLayout) content.getLayout()).show(content, BLANK);
        }
    }

    public void setCurrentPage(PageView currentPage) {
        if(currentPage == null)
            setModeState(BLANK);
        else
            ((CardLayout) pagesStack.getLayout()).show(pagesStack, currentPage.getModel().getName());
        //the line above changes page that's ont top of pages stack and
        // pages stack is displaying in slideshow mode and edit mode
        this.currentPage = currentPage;
    }

    @Override
    public void update(Notification notification) {

        Notification n = notification;
        JPanel curr = WorkspaceView.getCurrentlyOpened();

        if(n.getType() == NotificationType.DOUBLE_CLICK){
            if(curr != this)
                parentView.getParentView().display(this);
        }

        else if(n.getType() == NotificationType.ADD_ACTION){
            if(pages == null){
                pages = new ArrayList<PageView>();      //when u add page to document for the first time u go
                setModeState(SLIDE_SHOW);               //to edit mode by default
            }

            PageView newPage = new PageView((Page) n.getNotificationObject(), this);
            pages.add(newPage);
            pagesStack.add(newPage.getModel().getName(),newPage);       //adding in both
            setCurrentPage(newPage);

            if(curr == this)
                parentView.getParentView().display(this); //setting most resent added page to the
        }

        else if(n.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeDocument(this);
            if( curr == this ||
                    curr == parentView)
                parentView.display();
        }

        else if(n.getType() == NotificationType.RENAME_ACTION){
            if( curr == parentView)
                parentView.display();
            else if( curr == this )
                parentView.getParentView().display(this);
        }

        else if(n.getType() == NotificationType.AUTHOR_SET){
            author.setText(model.getAuthor());
            if(curr == this)
                repaint();

        }
        else if(n.getType() == NotificationType.THEME_SET){
            if(pages == null)
               return;
            for(PageView pageView : pages){
                pageView.setBackgroundTheme();
            }
        }
    }

    public void removePage(PageView toRemove){
        int pageIndex = pages.indexOf(toRemove);

        if(currentPage == toRemove){
            if(pages.size() == 1) {
                setModeState(BLANK);
                setCurrentPage(null);
            }
            else if(pageIndex >= 1)
                setCurrentPage(pages.get(pageIndex - 1));
            else if(pageIndex < pages.size() - 1)
                setCurrentPage(pages.get(pageIndex + 1));
        }
        pagesStack.remove(toRemove);
        pages.remove(toRemove);
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

    public PageView getCurrentPage() {
        return currentPage;
    }

    public ArrayList<PageView> getPages(){
        return pages;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public JPanel getPagesStack() {
        return pagesStack;
    }
}
