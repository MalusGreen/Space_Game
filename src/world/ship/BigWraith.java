package world.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Vector;

import world.ship.weapons.Weapon;
import world.ship.weapons.ammo.Ammo;

public class BigWraith extends Enemy {
	private double tyo=0, txo=0, tyn=0, txn=0;
	private final double c =.5;
	
	public BigWraith(double x, double y) {
		super(x, y);
		size = 5;
		speed=1;
		health=10;
		// TODO Auto-generated constructor stub
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
		g.setColor(Color.YELLOW);
		g.drawOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.MAGENTA);
		g.drawLine((int)(x-size*1.5), (int)y-6, (int)(x+size*1.5*health/10), (int)y-6);
		
		for(Weapon i: weapons){
			i.draw(g, x, y, angle);
		}

	}

	public void target(Ship user){
		//Targets user.
		tyo = tyn;
		txo = txn;
		tyn = user.getY();
		txn = user.getX();
		{
			//getWeapons().get(0).setTarget(user);
			
			pursuit();
			double a=tx-x, b=ty-y;
			double hyp=Math.sqrt(a*a+b*b);
			dx=a*speed/hyp;
			dy=b*speed/hyp;
		}
		
		//System.out.println("o: "+tyn+" t:"+ty);
		//System.out.println("o: "+txn+" t:"+tx);
		
	}
	
	private void pursuit(){
		Vector<Double> targetVector = getVector();
		//T= Dc
		//idk how c works, turning parameter apparently? I took rad/frame divided by accel/frame
		double T = getDistance()*c;
		double oAngle = getNewAngle(dx, dy);
		double tAngle = targetVector.get(3);
		if (tAngle+10>oAngle && tAngle-10<oAngle){
			
			//T=0.05; //scaling T if you go directly towards them
		} else if (tAngle+5>oAngle && tAngle-5<oAngle){
			T = 0; //down to 0 if the angle gets close enough
		}
		//double T = 10;
		double xs = targetVector.get(1);
		double ys = targetVector.get(2);
		double fx = txn+xs*T;
		double fy = tyn+ys*T;
		tx = fx;
		ty = fy;
		
		
		//System.out.println("Target:" +tx+","+ty);
	
		
		
	}
	
	private Vector<Double> getVector(){
		Vector<Double> tv = new Vector<Double>();
		double xspeed = txn-txo;
		double yspeed = tyn-tyo;
		double tSpeed = Math.sqrt(Math.pow(xspeed, 2)+ Math.pow(yspeed, 2));
		tv.add(tSpeed);
		tv.add(xspeed);
		tv.add(yspeed);
		double tAngle = getNewAngle(xspeed, yspeed);
		tv.add(tAngle);
		return tv;
	}
	
	private double getNewAngle(double xs, double ys){
		double angle = Math.atan(xs/ys);
		boolean ypos = xs>0;
		boolean xpos = ys>0;
		if ((ypos && !xpos) || (!ypos&& xpos)){
			angle+=Math.PI;
		}
		return angle;
	}
	
	private double getDistance(){
		
		double Dx = txn-x;
		double Dy = tyn-y;
		double Dt = Math.sqrt(Math.pow(Dx, 2)+Math.pow(Dy, 2));
		
		return Dt;
	}

	@Override
	public void avoid(Ship user) {
		// TODO Auto-generated method stub

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
