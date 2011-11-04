package org.areska;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class GridPainter extends Canvas {
	private Grid grid;
	public static final int Y_OFFSET = 80;
	public static final int X_OFFSET = 60;
	
	private final Toolkit t = getToolkit();
	Image creatureImage = t.getImage( "images/standing.gif" );
	Image treeImage = t.getImage( "images/tree.gif" );
	Image shroomImage = t.getImage( "images/shroom.gif" );

	
	public Image getShroomImage()
    {
        return shroomImage;
    }

    public Image getTreeImage()
    {
        return treeImage;
    }

    public Image getCreatureImage()
    {
        return creatureImage;
    }

    public GridPainter() {
		super();
	}
	
	@Override
	public void paint(Graphics g) {
		for( Thing thing : grid.getThings() ) {
		    thing.paint( g, thing.getX() + X_OFFSET, thing.getY() + Y_OFFSET );
		}
	}
	
	private Image buffer = null;
	@Override
    public void update( Graphics g ) {
	    if (buffer == null) buffer = createImage( getBounds().width, getBounds().height );
    	Graphics offScreenGraphics = buffer.getGraphics();
    	offScreenGraphics.clearRect( 0, 0, getBounds().width, getBounds().height );
	    paint(offScreenGraphics);
	    g.drawImage(buffer, 0, 0, this);
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

}
