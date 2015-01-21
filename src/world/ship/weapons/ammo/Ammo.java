package world.ship.weapons.ammo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import world.ship.Ship;

public abstract class Ammo{
	Ship target;
	double tx, ty;
	double x,y;
	double angle;
	double dx, dy;
	double speed;
	int size;
	int range;
	int damage;
	
	public Ammo(double x, double y, double angle, double speed){
		this.angle=angle;
		this.x=x;
		this.y=y;
		this.speed=speed;
		dx=Math.cos(angle)*speed;
		dy=-Math.sin(angle)*speed;
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	//Targets closest enemy.
	private void lockOn(Ammo ammo, ArrayList<Ship> enemies){
		if(!enemies.isEmpty()){
			target=enemies.get(0);
			for(Ship i:enemies){
				target=closest(ammo, target,i);
			}
		}
	}
	
	private Ship closest(Ammo ammo, Ship s1, Ship s2){
		if(distCalc(ammo.getX(),ammo.getY(),s1.getX(),s1.getY())>distCalc(ammo.getX(),ammo.getY(),s2.getX(),s2.getY())){
			return s2;
		}
		return s1;
	}
	
	private double distCalc(double x1, double y1, double x2, double y2){
		return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
	}
	
	//Getters and Setters
	public void setTarget(Ship target){
		this.target=target;
	}
	public void setRange(int range) {
		this.range=range;
	}
	public int getRange(){
		return range;
	}
	public double getDamage(){
		return damage;
	}
	public double getX() {
		return x;
	}
	public double getY(){
		return y;
	}
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,size,size);
	}
}
