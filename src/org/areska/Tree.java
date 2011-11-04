package org.areska;

import java.awt.Graphics;
import java.awt.Image;

public class Tree extends Thing {

    @Override
    public void paint( Graphics g, int x, int y ) {
        Image image = App.getGridPainter().getTreeImage();
        
        //g.drawString(""+power, x, y);
        g.drawImage( image, x, y, null );
        //g.setColor( Color.BLACK );
    }

    public void onTick()
    {
    }

}
