package app.view.state;
import app.model.repository.Page;

import app.view.repository.PageView;
import java.awt.*;
import java.awt.event.MouseEvent;

public class AddSlotState implements  IDrawState{


    @Override
    public void mousePressed(MouseEvent e) {
        PageView p = ((PageView.PageFramework)e.getSource()).getPage();

        float scale =  600f / p.getPageFramework().getWidth(); //WE SCALE MODEL INPUT COORDINATES RELATIVE TO PANEL WIDTH OF 600px
        ((Page)p.getModel()).addSlot(new Point((int)(e.getPoint().x * scale),(int)(e.getPoint().y  * scale)));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

}
