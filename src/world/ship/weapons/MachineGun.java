package world.ship.weapons;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.Ship;
import world.ship.weapons.ammo.*;

public class MachineGun extends Weapon{
	public MachineGun(){
		super();
		size=9;
		damage=1;
	}
	@Override
	public void draw(Graphics g, double x,double y, double angle) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.drawLine((int)x,(int)y,(int)(x+size*Math.cos(angle)),(int)(y-size*Math.sin(angle)));
		for(int i=0;i<ammo.size();i++){
			ammo.get(i).draw(g);
		}
	}
	@Override
	public void fire(double x, double y, double angle) {
		ammo.add(new Bullet(x, y, angle));
	}
	@Override
	public ArrayList<Ammo> getBullets() {
		// TODO Auto-generated method stub
		return ammo;
	}
	@Override
	public void update(double x, double y, ArrayList<Ship> enemies) {
		// TODO Auto-generated method stub
		remove();
	}
	
}
