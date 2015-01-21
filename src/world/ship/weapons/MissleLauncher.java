package world.ship.weapons;

import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.Ship;
import world.ship.weapons.ammo.*;

public class MissleLauncher extends Weapon{
	public MissleLauncher(){
		super();
		size=0;
		firerate=100;
	}
	
	@Override
	public void draw(Graphics g, double x, double y, double angle) {
		// TODO Auto-generated method stub
		if(cooldown>=-20*firerate){
			cooldown--;
		}
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
	

	@Override
	public void update(double x, double y, ArrayList<Ship> enemies) {
		
	}
	
	
}
