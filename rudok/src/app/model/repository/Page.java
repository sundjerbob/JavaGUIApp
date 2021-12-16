package app.model.repository;

import app.model.node.*;
import app.observer.Notification;
import app.observer.NotificationType;

import java.awt.*;
import java.util.ArrayList;


public class Page extends NodeModel {

    private ArrayList<Slot> slots;

    public Page(String name, Document parent) {
        super(name, parent);
        slots = new ArrayList<>();
    }

    public void addSlot(Point point) {
        Slot newSlot = new Slot(new Point(point.x - 50,point.y - 50));
        slots.add(newSlot);//first we add than we not
        notifySubscribers(new Notification(newSlot, NotificationType.SLOT_ADDED));
    }

    public void removeSlot(Slot toRemove) {
        if(!slots.contains(toRemove))
            return;
        int i = slots.indexOf(toRemove);
        slots.remove(toRemove);
        notifySubscribers(new Notification( i, NotificationType.SLOT_REMOVED));
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
