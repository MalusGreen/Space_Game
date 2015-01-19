package world.terrain;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Terrain {
	protected double x,y;
	protected double size;
	public Terrain(){
		
	}
	public abstract void draw(Graphics g);
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,(int)size,(int)size);
	}
}
