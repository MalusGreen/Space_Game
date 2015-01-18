package world.ship.weapons;

import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.Enemy;
import world.ship.Ship;
import world.ship.weapons.ammo.Ammo;
import world.ship.weapons.ammo.Bullet;

public abstract class Weapon {
	protected int size;
	protected double damage;
	protected double firerate;
	protected double cooldown;
	protected ArrayList<Ammo> ammo;
	protected Ship target;
	
	public Weapon(){
		ammo=new ArrayList<Ammo>();
		cooldown=0;
	}
	//Draws and updates, will correct later.
	public abstract void draw(Graphics g, double x, double y, double angle);
	
	//Creates instances of ammo type. Adds to ArrayList<> ammo;
	public abstract void fire(double x, double y, double angle);
	
	//Updates location of the weapon, not in use yet, will be used later in rotation and ship customization.
	public abstract void update(double x, double y, ArrayList<Ship> enemies);
	
	//returns ammo list
	public abstract ArrayList<Ammo> getBullets();
	
	//Returns the ship that the weapon has locked onto.
	public void setTarget(Ship target) {
		// TODO Auto-generated method stub
		this.target=target;
	}
	
	public void remove(){
		for(int i=0;i<ammo.size();i++){
			if(ammo.get(i).getRect().x>1000||ammo.get(i).getRect().y>800||ammo.get(i).getRect().x<0||ammo.get(i).getRect().y<0){
				ammo.remove(i);
				i--;
			}
			else if(ammo.get(i).getRange()<=0){
				ammo.remove(i);
				i--;
			}
		}
	}
	
	public void lockOn(Ammo ammo, ArrayList<Ship> enemies){
		if(!enemies.isEmpty()){
			target=enemies.get(0);
			for(Ship i:enemies){
				target=closest(ammo, target,i);
			}
		}
	}
	//Compares distance of two ships to this ship and returns the one closer
	public Ship closest(Ammo ammo, Ship s1, Ship s2){
		if(distCalc(ammo.getX(),ammo.getY(),s1.getX(),s1.getY())>distCalc(ammo.getX(),ammo.getY(),s2.getX(),s2.getY())){
			return s2;
		}
		return s1;
	}
	//Return distance^2
	public double distCalc(double x1, double y1, double x2, double y2){
		return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
	}
	
	public double getDamage(){
		return damage;
	}
}
