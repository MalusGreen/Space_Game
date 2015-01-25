package world.ship.weapons;

import java.awt.Graphics;

import world.ship.*;
import world.ship.weapons.ammo.*;

public abstract class Weapon {
	protected int size;
	protected double firerate;
	protected double cooldown;
	protected Ship target;
	protected int MaxRally;
	public Weapon(){
		cooldown=0;
	}
	//Draws and updates, will correct later.
	public abstract void draw(Graphics g, double x, double y, double angle);
	
	//Creates instances of ammo type. Adds to ArrayList<> ammo;
	public abstract Ammo fire(double x, double y, double angle);
	
	//Updates cooldowns.
	public void update(){
		if(cooldown>=-MaxRally*firerate){
			cooldown--;
		}
	}
	
	//Returns the ship that the weapon has locked onto.
	public void setTarget(Ship target) {
		// TODO Auto-generated method stub
		this.target=target;
	}

	
	
	
	
}
