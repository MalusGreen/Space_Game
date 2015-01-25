package world.ship.weapons.ammo;

import java.awt.Color;
import java.awt.Graphics;

public class Shell extends Ammo{
	public Shell(double x, double y, double angle){
		super(x, y, angle, 6);
		size=1;
		range=1;
		damage=5;
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
