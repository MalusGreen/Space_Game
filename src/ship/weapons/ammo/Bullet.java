package ship.weapons.ammo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Bullet extends Ammo{
	
	public Bullet(double x, double y, double angle){
		super(x, y, angle);
		speed=6;
		size=1;
	}
	@Override
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 1, 1);
		
		update();
	}

	@Override
	public void update() {
		x+=dx;
		y+=dy;
	}
}
