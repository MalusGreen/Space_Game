package world.ship.weapons;

import java.awt.Color;
import java.awt.Graphics;

import world.ship.weapons.ammo.*;

public class Spitter extends Weapon{
	public Spitter(){
		super();
		size=9;
		firerate=25;
		MaxRally=0;
	}
	public void draw(Graphics g, double x, double y, double angle) {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.drawLine((int)x,(int)y,(int)(x+size*Math.cos(angle)),(int)(y-size*Math.sin(angle)));
	}
	@Override
	public Ammo fire(double x, double y, double angle) {
		if(cooldown<0){
			return null;
		}
		cooldown-=firerate;
		return new Shell(x+size*Math.cos(angle),y-size*Math.sin(angle), angle);
	}
}
