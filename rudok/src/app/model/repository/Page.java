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
        Slot newSlot = new Slot(new Point(point.x - 50,point.y - 50),this);
        slots.add(newSlot);
        notifySubscribers(new Notification(newSlot, NotificationType.SLOT_ADDED));
    }

    public void removeSlot(Slot toRemove) {
        if(!slots.contains(toRemove))
            return;
        slots.remove(toRemove);
        toRemove.notifySubscribers(new Notification( toRemove, NotificationType.SLOT_REMOVED));
    }

    public void setSlotSettings(Color fil,Color line,int stroke,Slot sel){
        if(sel != null)
        {   System.out.println("IMA LEBA");
            sel.setOutLineColor(line);
            sel.setStroke(stroke);
            sel.setInsideColor(fil);
        }
        else if(slots != null && !slots.isEmpty()) {
            for (Slot curr : slots) {
                System.out.println("MEnjamnjemanj");
                curr.setInsideColor(fil);
                System.out.println(line);
                curr.setStroke(stroke);
                curr.setOutLineColor(line);
            }
        }
        notifySubscribers(new Notification(this,NotificationType.SLOT_CHANGED));
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }
}
