package world.ship.weapons;

import java.awt.Color;
import java.awt.Graphics;


import world.ship.weapons.ammo.*;

public class MachineGun extends Weapon{
	public MachineGun(){
		super();
		size=9;
	}
	@Override
	public void draw(Graphics g, double x,double y, double angle) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.drawLine((int)x,(int)y,(int)(x+size*Math.cos(angle)),(int)(y-size*Math.sin(angle)));
	}
	@Override
	public Ammo fire(double x, double y, double angle) {
		return new Bullet(x, y, angle);
	}
	
}
