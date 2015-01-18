package world.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.weapons.*;
import world.ship.weapons.ammo.*;

public class RingWraith extends Enemy{
	public RingWraith(double x, double y){
		//Attributes are mostly 1.
		super(x, y);
		speed=1;
		health=1;
	}
	@Override
	public void target(Ship user){
		//Targets user.
		tx=user.getX();
		ty=user.getY();
		double a=tx-x, b=ty-y;
		double hyp=Math.sqrt(a*a+b*b);
		dx=a*speed/hyp;
		dy=b*speed/hyp;
	}
	@Override
	public void avoid(Ship user){
		
	}
	@Override
	public void drawShip(Graphics g) {
		// TODO Auto-generated method stub
		if(health<=0){
			g.setColor(Color.red);
			g.drawRect((int)x-size, (int)y-size, health*-1,health*-1);
			health--;
			return;
		}
		g.setColor(Color.yellow);
		g.drawOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.green);
		g.drawLine((int)(x-size*1.5), (int)y-6, (int)(x+size*1.5*health/10), (int)y-6);
	}

	@Override
	public void damage(Ship user) {
		ArrayList<Ammo> bullets;
		if(user.getRect().intersects(this.getRect())){
			user.setHealth(user.getHealth()-20);
			health=0;
		}
		for(Weapon item:user.getWeapons()){
			bullets=item.getBullets();
			for(Ammo i:bullets){
				if(i.getRect().intersects(this.getRect())){
					health-=item.getDamage();
					i.setRange(0);
				}
			}
		}
	}
}