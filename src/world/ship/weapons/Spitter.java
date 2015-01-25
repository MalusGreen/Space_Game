package world.ship.weapons;

import java.awt.Graphics;

import world.ship.weapons.ammo.*;

public class Spitter extends Weapon{
	public Spitter(){
		super();
		size=0;
		firerate=5;
	}
	public void draw(Graphics g, double x, double y, double angle) {
		// TODO Auto-generated method stub
		if(cooldown>=0){
			cooldown--;
		}
	}
	@Override
	public Ammo fire(double x, double y, double angle) {
		cooldown+=firerate;
		if(cooldown>=0){
			return null;
		}
		return new Shell(x,y,angle);
	}
}
