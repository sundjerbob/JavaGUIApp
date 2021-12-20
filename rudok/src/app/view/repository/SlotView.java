package app.view.repository;


import app.model.repository.Slot;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;


import java.awt.*;
import java.awt.geom.GeneralPath;


public class SlotView implements ISubscriber {

    private final Slot model;
    private final PageView parentView;
    private Shape shape;
    private boolean selected;

    public SlotView (Slot slot, PageView parent) {
        model = slot;
        model.addSubscriber(this);
        parentView = parent;
        shape = new GeneralPath();
    }

    void paint(Graphics g,  Object preview){
        Graphics2D g2 = (Graphics2D) g;
        int stroke = model.getStroke();
        float scale;
        if(preview instanceof PageView.PageThumbnail)
            scale = 140f / 600f;
        else
            scale = parentView.getPageFramework().getWidth() / 600f;

        g2.setStroke(new BasicStroke((int)Math.ceil(stroke * scale)));
        g2.setColor(model.getOutLineColor());
        setShape(preview);
        g2.draw(shape);

        g2.setColor(model.getInsideColor());
        g2.fill(shape);

        if(selected){
            g2.setStroke(new BasicStroke((int)Math.ceil(5 * scale)));
            g2.setColor(Color.blue);
            g2.draw(shape);
        }
    }

    private void setShape(Object preview){
        float scale;
        if(preview instanceof PageView.PageThumbnail)
            scale = 140f / 600f;
        else
            scale = parentView.getPageFramework().getWidth() / 600f;

        shape = new GeneralPath();
        ((GeneralPath)shape).moveTo((int)(model.getPoint().x * scale),(int)(model.getPoint().y * scale));
        ((GeneralPath)shape).lineTo((int)(model.getPoint().x  * scale + model.getSize().getWidth()  * scale) ,(int) (model.getPoint().y * scale));
        ((GeneralPath)shape).lineTo((int)(model.getPoint().x  * scale + model.getSize().getWidth()  * scale), (int)(model.getPoint().y * scale + model.getSize().height * scale));
        ((GeneralPath)shape).lineTo((int)(model.getPoint().x * scale),(int)(model.getPoint().y * scale + model.getSize().height  * scale));
        ((GeneralPath)shape).closePath();

    }

    public boolean elementAt(Point point){

        float scale = parentView.getPageFramework().getWidth() / 600f;
        if(model.getPoint().x * scale <= point.x && (model.getPoint().x + 100) * scale >= point.x &&
                model.getPoint().y * scale <= point.y && (model.getPoint().y + 100) * scale >= point.y)
        return true;

        return  shape.contains(point);
    }



    @Override
    public void update(Notification notification) {
        if(notification.getType() == NotificationType.SLOT_REMOVED) {

            parentView.getSlots().remove(this);

            if (parentView.getSelectedSlot() == this)
                parentView.selectSlot(null);

            else {
                parentView.getPageThumbnail().repaint();
                parentView.getPageFramework().repaint();
            }
        }

    }

    public void setSelected(boolean arg){
        selected = arg;
    }

    public Slot getModel() {
        return model;
    }

    public Shape getShape() {
        return shape;
    }
}
