package app.view.state;
import app.view.repository.PageView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectState implements IDrawState {

    private int xOffset;
    private int yOffset;
    private float scale;


    @Override
    public void mousePressed(MouseEvent e) {

        PageView p = ((PageView.PageFramework)e.getSource()).getPage();
        scale = p.getPageFramework().getWidth() / 600f   ;
        if(p.getSlots() != null)
            for(int i = p.getSlots().size() - 1; i >= 0; i--){
                if(p.getSlots().get(i).elementAt(e.getPoint())) {
                    xOffset =  e.getPoint().x - (int)(p.getSlots().get(i).getModel().getPoint().x * scale) ;
                    yOffset =  e.getPoint().y - (int)(p.getSlots().get(i).getModel().getPoint().y * scale);
                    p.selectSlot(p.getSlots().get(i));
                    return;
                }
            }
        p.selectSlot(null);

    }
    @Override
    public void mouseDragged(MouseEvent e){
        PageView p = ((PageView.PageFramework)e.getSource()).getPage();
        scale = 600f / p.getPageFramework().getWidth();
        if(p.getSelectedSlot() != null){

               p.getSelectedSlot().getModel().setPoint(new Point((int)(scale*(e.getPoint().x - xOffset)) ,(int)(scale*(e.getPoint().y - yOffset))));
        }
    }


}
