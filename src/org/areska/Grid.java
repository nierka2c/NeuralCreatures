package org.areska;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Grid implements Serializable {

	private static final long serialVersionUID = 1L;
	private int height, width;
	private final List<Thing> things;
	

	public List<Thing> getThings()
    {
        return things;
    }

    public Grid(int height, int width)
    {
        super();
        this.height = height;
        this.width = width;
        things = new ArrayList<Thing>();
    }

    public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
