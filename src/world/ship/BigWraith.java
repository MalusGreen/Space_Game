package world.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

import world.ship.weapons.MissleLauncher;

public class BigWraith extends Enemy {
	private double tyo=0, txo=0, tyn=0, txn=0;
	private final double c =.5;
	
	public BigWraith(double x, double y) {
		super(x, y);
		//TODO manage weapons nicely.
		//TODO Weapons now don't automatically get pick and each ship will have it's own set of weapons.
		
		//Enemy Attributes.
		team=1;
		size = 5;
		speed=1;
		MAXHEALTH=10;
		health=MAXHEALTH;
		//BigWraith Weapons.
		weapons.add(new MissleLauncher());
	}

	@Override
	public void drawShip(Graphics g) {
		if(health<=0){
			g.setColor(Color.red);
			g.drawRect((int)x-size, (int)y-size, health*-1,health*-1);
			health--;
			return;
		}
		g.setColor(new Color(255,255,0,50));
		g.fillOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.YELLOW);
		g.drawOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.MAGENTA);
		g.drawLine((int)(x-size*1.5), (int)y-size-5, (int)(x+size*(3.0*health/MAXHEALTH-1.5)), (int)y-size-5);
		//That machine looks awful on him right now. Lol
//		for(Weapon i: weapons){
//			i.draw(g, x, y, angle);
//		}
		
		
		
		
	}

	public void target(Ship user){
		//Targets user.
		tyo = tyn;
		txo = txn;
		tyn = user.getY();
		txn = user.getX();
		{
			//Weapon Cooldowns don't refresh right now if they are not drawn. I'll fix that.
			shoot();
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
		Vector<Double> connectVector = getConnectingVector();
		
		
		double distance = getDistance(txn, tyn);
		
		//T= Dc
		//idk how c works, turning parameter apparently? I took rad/frame divided by accel/frame
		double T = distance*c;

		boolean samex = (targetVector.get(1)>0 && connectVector.get(1)<0 )|| (targetVector.get(1)<0 && connectVector.get(1)>0 ) ;
		boolean samey = (targetVector.get(2)>0 && connectVector.get(2)<0 )|| (targetVector.get(2)<0 && connectVector.get(2)>0 ) ;

		
		if (samex && samey){
			T = 0.05;
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
	
	private Vector<Double> getConnectingVector(){
		Vector<Double> tv = new Vector<Double>();
		double xspeed = txn-x;
		double yspeed = tyn-y;
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
	
	private double getDistance(double tx,double ty){
		
		double Dx = tx-x;
		double Dy = ty-y;
		double Dt = Math.sqrt(Math.pow(Dx, 2)+Math.pow(Dy, 2));
		
		return Dt;
	}

	@Override
	public void avoid(Ship user) {
		// TODO Auto-generated method stub

	}

}
