package org.areska;
import java.awt.Graphics;
import java.io.Serializable;


public abstract class Thing  implements Serializable{

	protected int x, y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract void paint(Graphics g, int x, int y);

	public boolean isBlocking() {
		return false;
	}

}
