package world.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class RingWraith extends Enemy{
	
	private double tyo=0, txo=0, tyn=0, txn=0;
	private final double c =0.25;
	public RingWraith(double x, double y){
		//Attributes are mostly 1.
		super(x, y);
		team=1;
		size=3;
		speed=2.5;
		health=1;
	}
	@Override
	public void target(Ship user){
		//Targets user.
		tyo = tyn;
		txo = txn;
		tyn = user.getY();
		txn = user.getX();
		{
			
//			getWeapons().get(1).setTarget(user);
//			shoot();
			pursuit();
			double a=tx-x, b=ty-y;
			double hyp=Math.sqrt(a*a+b*b);
			dx=a*speed/hyp;
			dy=b*speed/hyp;
		}
		
		//System.out.println("o: "+tyn+" t:"+ty);
		//System.out.println("o: "+txn+" t:"+tx);
		
	}

	@Override
	public void drawShip(Graphics g) {
		//Create methods that control drawEngine, drawWeapons, drawArmor, drawMods... etc
		// TODO
		// TODO
		//!!!!!!!!!!!!!!!!!!!!!
		//There is now drawhealth, drawBody, drawWeapons, etc methods. Eventuall this should be moved to those.
		
		//Death Animation
		if(health<=0){
			g.setColor(Color.red);
			g.drawRect((int)x-size, (int)y-size, health*-1,health*-1);
			health--;
			return;
		}
		g.setColor(new Color(255,255,0,50));
		g.fillOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.yellow);
		g.drawOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(Color.green);
		g.drawLine((int)(x-size*1.5), (int)y-6, (int)(x+size*1.5*health/1), (int)y-6);
	}
	
	//Movement algorithm
	private void pursuit(){
		Vector<Double> targetVector = getVector();
		Vector<Double> connectVector = getConnectingVector();
		//T= Dc
		//idk how c works, turning parameter apparently? I took rad/frame divided by accel/frame
		double T = getDistance()*c;
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
	
	private double getDistance(){
		
		double Dx = txn-x;
		double Dy = tyn-y;
		double Dt = Math.sqrt(Math.pow(Dx, 2)+Math.pow(Dy, 2));
		
		return Dt;
	}
	@Override
	public void avoid(Ship user){
		
	}
}
