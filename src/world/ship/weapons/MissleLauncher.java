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
		damage=2;
	}
	
	@Override
	public void draw(Graphics g, double x, double y, double angle) {
		// TODO Auto-generated method stub
		for(int i=0;i<ammo.size();i++){
			ammo.get(i).draw(g);
			ammo.get(i).setTarget(target);
		}
		if(cooldown>=-20*firerate){
			cooldown--;
		}
	}

	@Override
	public void fire(double x, double y, double angle) {
		if(cooldown>=0){
			return;
		}
		Missle missle=new Missle(x,y,angle);
		missle.setTarget(target);
		ammo.add(missle);
		cooldown+=firerate;
	}
	

	@Override
	public void update(double x, double y, ArrayList<Ship> enemies) {
		remove();
		for(Ammo i:ammo){
			lockOn(i,enemies);
		}
	}

	@Override
	public ArrayList<Ammo> getBullets() {
		// TODO Auto-generated method stub
		return ammo;
	}
	
	
}
