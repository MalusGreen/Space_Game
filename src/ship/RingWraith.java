package ship;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import ship.weapons.Weapon;
import ship.weapons.ammo.Bullet;

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
		//For now it only gets one weapon.
		ArrayList<Bullet> bullets=user.getWeapons().get(0).getBullets();
		if(user.getRect().intersects(this.getRect())){
			user.setHealth(user.getHealth()-20);
			health=0;
		}
		for(int i=0;i<bullets.size();i++){
			if(bullets.get(i).getRect().intersects(this.getRect())){
				health--;
				bullets.remove(i);
				i--;
			}
			else if(bullets.get(i).getRect().x>1000||bullets.get(i).getRect().y>800||bullets.get(i).getRect().x<0||bullets.get(i).getRect().y<0){
				bullets.remove(i);
				i--;
			}
		}
	}
}
