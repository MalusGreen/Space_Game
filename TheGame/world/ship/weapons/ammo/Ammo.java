package world.ship.weapons.ammo;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import world.ship.Ship;

public abstract class Ammo{
	protected double tx, ty;
	protected double x,y;
	protected double angle;
	protected double dx, dy;
	protected double speed;
	protected int size;
	protected int range;
	protected int damage;
	protected int team;
	protected Ship target;
	
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
	public void lockOn(ArrayList<Ship> enemies){
		if(!enemies.isEmpty()){
			target=enemies.get(0);
			for(Ship i:enemies){
				target=closest(this, target,i);
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
	public void setTeam(int team){
		this.team=team;
	}
	public int getTeam(){
		return team;
	}
	public void setTarget(Ship target){
		this.target=target;
	}
	public void setRange(int range) {
		this.range=range;
	}
	public int getRange(){
		return range;
	}
	public int getDamage(){
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
