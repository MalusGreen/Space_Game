package world.ship.weapons.ammo;

import java.awt.Graphics;
import java.awt.Rectangle;

import world.ship.Ship;

public abstract class Ammo{
	Ship target;
	double tx, ty;
	double x,y;
	double angle;
	double dx, dy;
	int speed;
	int size;
	int range;
	public Ammo(double x, double y, double angle, int speed){
		this.angle=angle;
		this.x=x;
		this.y=y;
		this.speed=speed;
		dx=Math.cos(angle)*speed;
		dy=-Math.sin(angle)*speed;
	}
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,size,size);
	}
	public void setTarget(Ship target){
		this.target=target;
	}
	public int getRange(){
		return range;
	}
	public abstract void update();
	public abstract void draw(Graphics g);
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public double getY(){
		return y;
	}
	public void setRange(int range) {
		this.range=range;
	}
}
