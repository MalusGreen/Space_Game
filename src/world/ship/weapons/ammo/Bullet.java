package world.ship.weapons.ammo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Bullet extends Ammo{
	
	public Bullet(double x, double y, double angle){
		super(x, y, angle, 6);
		size=1;
		range=1;
	}
	@Override
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, size, size);
		update();
	}

	@Override
	public void update() {
		x+=dx;
		y+=dy;
	}
}
