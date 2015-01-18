package world.ship.weapons;

import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.Ship;
import world.ship.weapons.ammo.*;

public class Spitter extends Weapon{
	public Spitter(){
		super();
		size=0;
		firerate=5;
	}
	public void draw(Graphics g, double x, double y, double angle) {
		// TODO Auto-generated method stub
		for(int i=0;i<ammo.size();i++){
			ammo.get(i).draw(g);
			ammo.get(i).setTarget(target);
		}
		if(cooldown>=0){
			cooldown--;
		}
	}
	@Override
	public void fire(double x, double y, double angle) {
		if(cooldown>=0){
			return;
		}
		Shell shell=new Shell(x,y,angle);
		ammo.add(shell);
		cooldown+=firerate;
	}
	@Override
	public void update(double x, double y, ArrayList<Ship> enemies) {
//		for(Ammo i:ammo){
//			lockOn(i,enemies);
//		}
	}
	@Override
	public ArrayList<Ammo> getBullets() {
		// TODO Auto-generated method stub
		return ammo;
	}
}
