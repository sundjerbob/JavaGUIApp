package app.model.repository;

import app.observer.IPublisher;
import app.observer.ISubscriber;
import app.observer.Notification;

import java.awt.*;
import java.util.ArrayList;

public class Slot implements IPublisher {

    private Point point;
    private int with = 100, height = 100;
    private Color outLineColor = Color.yellow;
    private Color insideColor = Color.black;
    private Stroke stroke ;
    private ArrayList<ISubscriber> subscribers;

    public Slot(Point point) {
        this.point = point;
        stroke = new BasicStroke(5);
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

    public void setInsideColor(Color insideColor) {
        this.insideColor = insideColor;
    }

    public void setOutLineColor(Color outLineColor) {
        this.outLineColor = outLineColor;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public Color getOutLineColor() {
        return outLineColor;
    }

    public Color getInsideColor() {
        return insideColor;
    }

    public int getWith() {
        return with;
    }

    public int getHeight() {
        return height;
    }

    public Point getPoint() {
        return point;
    }
}
