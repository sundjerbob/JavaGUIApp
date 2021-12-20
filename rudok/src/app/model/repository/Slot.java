package app.model.repository;

import app.observer.IPublisher;
import app.observer.ISubscriber;
import app.observer.Notification;
import app.observer.NotificationType;

import java.awt.*;
import java.util.ArrayList;

public class Slot implements IPublisher {

    private final Page parent;
    private Point point;
    private Dimension size ;
    private Color outLineColor;
    private Color insideColor;
    private int stroke;
    private ArrayList<ISubscriber> subscribers;

    public Slot(Point point,Page page) {
        this.point = point;
        parent = page;
        size = new Dimension(100,100);
        stroke = 5;
        insideColor = Color.black;
        outLineColor = Color.yellow;
    }


    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscribers == null)
            subscribers = new ArrayList<>();
        if(!subscribers.contains(subscriber))
            subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for(ISubscriber curr : subscribers)
            curr.update(notification);
    }




    void setInsideColor(Color insideColor) {
        this.insideColor = insideColor;
    }

    void setOutLineColor(Color outLineColor) {
        this.outLineColor = outLineColor;
    }

    void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public int getStroke() {
        return stroke;
    }


    public Color getOutLineColor() {
        return outLineColor;
    }

    public Color getInsideColor() {
        return insideColor;
    }


    public Dimension getSize() {
        return size;
    }



    public Point getPoint() {
        return point;
    }

    public void setSize(Dimension d){
        size = d;
    }
    public void setPoint(Point point) {
        this.point = point;
        parent.notifySubscribers(new Notification(point, NotificationType.SLOT_RELOCATED));
    }
}
