package world.ship.weapons;

import java.awt.Color;
import java.awt.Graphics;

import world.ship.weapons.ammo.*;

public class MissleLauncher extends Weapon{
	public MissleLauncher(){
		super();
		size=9;
		firerate=100;
		//MaxRally controls how many rounds you can save in a weapon for a burst.
		MaxRally=20;
	}
	
	@Override
	public void draw(Graphics g, double x, double y, double angle) {
		g.setColor(Color.orange);
		g.drawLine((int)x,(int)y,(int)(x+size*Math.cos(angle)),(int)(y-size*Math.sin(angle)));
	}

	@Override
	public Ammo fire(double x, double y, double angle) {
		if(cooldown<0){
			return null;
		}
		cooldown-=firerate;
		Missle missle=new Missle(x+size*Math.cos(angle),y-size*Math.sin(angle), angle);
		missle.setTarget(target);
		return missle;
	}
}
