package app.view.state;

import app.model.repository.Page;

import app.view.repository.PageView;
import java.awt.event.MouseEvent;

public class DelSlotState implements IDrawState{


    @Override
    public void mousePressed(MouseEvent e) {
        PageView p = ((PageView.PageFramework)e.getSource()).getPage();
        if(p.getSlots() != null){

            for(int i = p.getSlots().size() - 1;i >= 0; i--){
                if(p.getSlots().get(i).elementAt( e.getPoint() ) ) {
                    System.out.println("delete");
                    ((Page)p.getModel()).removeSlot(p.getSlots().get(i).getModel());
                    return;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}

