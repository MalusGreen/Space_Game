package world.ship.weapons;

import java.awt.Graphics;

import world.ship.weapons.ammo.*;

public class MissleLauncher extends Weapon{
	public MissleLauncher(){
		super();
		size=0;
		firerate=100;
		//MaxRally controls how many rounds you can save in a weapon for a burst.
		MaxRally=20;
	}
	
	@Override
	public void draw(Graphics g, double x, double y, double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ammo fire(double x, double y, double angle) {
		if(cooldown>=0){
			return null;
		}
		cooldown+=firerate;
		Missle missle=new Missle(x,y,angle);
		missle.setTarget(target);
		return missle;
	}
}
