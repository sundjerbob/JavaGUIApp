package app.view.repository;

import app.controller.command.AddCommand;
import app.model.factory.Factory;
import app.model.repository.Document;
import app.model.repository.Page;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.DrawToolBar;
import app.view.gui.MainFrame;
import app.view.state.*;
import app.view.tree.model.TreeItem;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

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
    private final DrawToolBar drawToolBar;



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

        /******BLANK PANEL******/
        JPanel blank = new JPanel(new BorderLayout());
        blank.setBackground(Color.cyan.darker());
        JLabel blankLabel = new JLabel("Add first slide!",loadIcon("images/firstPage.png"), SwingConstants.CENTER);
        blankLabel.setFont(new Font(Font.SERIF,Font.PLAIN,20));
        blankLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(DocumentView.this.model));
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
            }
        });
        blankLabel.setBackground(Color.CYAN.darker());
        blankLabel.setOpaque(true);
        blankLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        blank.add(blankLabel, BorderLayout.CENTER);

        drawToolBar = new DrawToolBar();
        editMode = new EditMode(this,drawToolBar);
        slideshowMode = new SlideshowMode(this);

        documentContent.add(editMode,EDIT_MODE);//mode 1(default mode)
        documentContent.add(slideshowMode,SLIDE_SHOW);//mode 2
        documentContent.add(blank,BLANK);//means u have no pages to work with (gives u an option to make new one)
        setModeState(BLANK);//when u make new document it has no pages
    }



    public void setCurrentPage(int currentPageIndex) {

        if(pages != null && !pages.isEmpty() && currentPage > -1 && pages.size() > currentPage)
            pages.get(currentPage).setSel(false);

        if(currentPageIndex == -1)
            setModeState(BLANK);

        else {
            PageView curr = pages.get(currentPageIndex);
            curr.setSel(true);
            ((CardLayout) pagesStack.getLayout()).show(pagesStack, curr.getModel().getName());//curr page on top
            slideLabel.setText(curr.getModel().getName());
            stateManager.getCurrModeState().set();//if we have pages to work with we set current state
            repaint();
            TreeItem item = MainFrame.getInstance().getITree().findItemByModel(curr.getModel());
            MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));

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
            return;
        }
        if(currentPage == index){

            if(index < pages.size()) {
                System.out.println("if 1");
                setCurrentPage(index);
            }
            else {
                System.out.println("if 2");
                setCurrentPage(index - 1);
            }
        }

        editMode.updateThumbnail();
        slideshowMode.updateArrows();
    }

    public void switchMode() {  //document toggles between 2 states with this method (initially state is EditState)
        if(stateManager.getCurrModeState() instanceof EditState)
            stateManager.setSlideshowState();
        else
            stateManager.setEditState();
    }

    public void setDrawState(Object o){ // this method is for setting draw state of this document

        if(o instanceof AddSlotState){
            stateManager.setAddSlotState();
            pages.get(currentPage).selectSlot(null);
        }

        if(o instanceof SelectState){
            stateManager.setSelectState();
        }

        if(o instanceof DelSlotState) {
            stateManager.setDelSlotState();
            pages.get(currentPage).selectSlot(null);
        }

         drawToolBar.setCurrActive(o);
    }

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

            if(  pages.size() == 1){
                setCurrentPage(pages.indexOf(newPage));
                stateManager.getCurrModeState().set();
            }

            if(WorkspaceView.getCurrentlyOpened() != this) {
                parentView.getParentView().display(this);
            }

            editMode.updateThumbnail();
            slideshowMode.updateArrows();
        }

        else if(notification.getType() == NotificationType.REMOVE_ACTION) {
            parentView.removeDocument(this);
            if( curr == this || curr == parentView)
                parentView.display();
        }

        else if(notification.getType() == NotificationType.RENAME_ACTION) {
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

            if(pages != null)
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
