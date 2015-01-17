package ship.weapons.ammo;

import java.awt.Graphics;
import java.awt.Rectangle;

import ship.Ship;

public abstract class Ammo{
	Ship target;
	double tx, ty;
	double x,y;
	double angle;
	double dx, dy;
	int speed;
	int size;
	public Ammo(double x, double y, double angle){
		this.angle=angle;
		this.x=x;
		this.y=y;
		dx=Math.cos(angle)*speed;
		dy=-Math.sin(angle)*speed;
	}
	public Rectangle getRect(){
		return new Rectangle((int)x,(int)y,size,size);
	}
	public abstract void update();
	public abstract void draw(Graphics g);
}
