package app.view.repository;

import app.model.repository.Slot;
import java.awt.*;
import java.awt.geom.GeneralPath;


public class SlotView {

    private Slot model;
    private Point point;
    private int with,height;

    public SlotView(Slot slot) {
        model = slot;
        point = slot.getPoint();
        with = slot.getWith();
        height = slot.getHeight();
    }

    void paint(Graphics g){
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(model.getStroke());
    g2.setColor(model.getOutLineColor());
    GeneralPath path =new GeneralPath();
    path.moveTo(point.x,point.y);
    path.lineTo(point.x + with , point.y);
    path.lineTo(point.x + with, point.y + height);
    path.lineTo(point.x,point.y + height );
    path.closePath();
    g2.draw(path);
    g2.setColor(model.getInsideColor());
    g2.fill(path);
    }

}
