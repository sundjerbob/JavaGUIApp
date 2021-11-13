package app.view.repository;

import app.model.repository.Document;
import app.model.repository.Page;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;
import app.view.gui.MainFrame;
import app.view.tree.model.TreeItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;

public class DocumentView extends JPanel implements ISubscriber {

    private Document model;
    private FileView parentView;
    private ArrayList<PageView> pages;
    private PageView currentPage;
    private JLabel name;
    private JPanel center;

    public DocumentView(Document model,FileView parentView){
        super(new BorderLayout());
        this.model = model;
        this.parentView = parentView;
        model.addSubscriber(this);

        setBackground(Color.cyan.darker());
        name = new JLabel( "     " + model.getName());
        name.setPreferredSize(new Dimension(0,40));
        name.setHorizontalAlignment(SwingConstants.LEFT);
        name.setVerticalAlignment(SwingConstants.CENTER);
        add(name,BorderLayout.NORTH);

        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(0, 40));
        bottom.setBackground(Color.CYAN.darker());
        add(bottom,BorderLayout.SOUTH);

        JPanel left = new JPanel(new BorderLayout());
        Button previousPage = new Button(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(getIndexOfPage(currentPage) > 0)
                    setCurrentPage(pages.get(getIndexOfPage(currentPage) - 1));
            }
        };
        previousPage.setIcon(loadIcon("images/prev.png"));
        left.setBorder(new EmptyBorder(100, 10, 100, 10));
        left.add(previousPage,BorderLayout.CENTER);

        JPanel right = new JPanel(new BorderLayout());
        Button nextPage = new Button(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(getIndexOfPage(currentPage) != pages.size() - 1){
                    setCurrentPage(pages.get(getIndexOfPage(currentPage) + 1));
                }
            }
        };
        nextPage.setIcon(loadIcon("images/next.png"));
        right.setBorder(new EmptyBorder(100, 10, 100, 10));
        right.add(nextPage, BorderLayout.CENTER);
        add(right,BorderLayout.EAST);
        add(left, BorderLayout.WEST);

        center = new JPanel(new BorderLayout());
        add(center,BorderLayout.CENTER);
    }


    @Override
    public void update(Object notification) {

        Notification n = (Notification) notification;
        JPanel curr = WorkspaceView.getCurrentlyOpened();

        if(n.getType() == NotificationType.ADD_ACTION){
            if(pages == null)
                pages = new ArrayList<PageView>();
           PageView newPage = new PageView((Page) n.getNotificationObject(),this);
           pages.add(newPage);
           setCurrentPage(newPage);
            if(curr == this)
                display(); //setting most resent added page to the
        }
        else if(n.getType() == NotificationType.REMOVE_ACTION){
            parentView.removeDocument(this);
            if( curr == this ||
                    WorkspaceView.getCurrentlyOpened() == parentView)
                parentView.display();
        }
        else if(n.getType() == NotificationType.RENAME_ACTION){
            if( curr == parentView)
                parentView.display();
            else if( curr == this ){
                name.setText("     " + model.getName());
                display();
            }

        }
    }

    public void display(){
        parentView.getParentView().display(this);
    }

    public Document getModel() {
        return model;
    }

    public ArrayList<PageView> getPages(){
        return pages;
    }

    public int getIndexOfPage(PageView page){
        for(int i = 0; i < pages.size(); i++){
            if(pages.get(i) == page )
                return i;
        }
        return -1;
    }

    public FileView getParentView() {
        return parentView;
    }

    public PageView getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(PageView currentPage) {
        center.removeAll();
        if(currentPage == null){
            updateUI();
            return;
        }
        this.currentPage = currentPage;
        center.add(currentPage,BorderLayout.CENTER);
        TreeItem item =  MainFrame.getInstance().getITree().findItemByModel(currentPage.getModel());
        MainFrame.getInstance().getITree().getTreeView().setSelectionPath(new TreePath(item.getPath()));


        updateUI();
    }

    public void removePage(PageView me){
        pages.remove(me);
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
}
