package world.ship.weapons.ammo;

import java.awt.Color;
import java.awt.Graphics;

public class Missle extends Ammo{
	public Missle(double x, double y, double angle){
		super(x,y,angle,2);
		size=2;
		//TODO Fix range
		range=500;
	}
	@Override
	public void update() {
		if(range<450&&target!=null){
			tx=target.getX();
			ty=target.getY();
			double a=tx-x, b=ty-y;
			double hyp=Math.sqrt(a*a+b*b);
			a=a*speed/hyp;
			b=b*speed/hyp;
//				if(dx!=a){
//					if(dx>a){
//						dx-=speed/50.0;
//					}
//					else{
//						dx+=speed/50.0;
//					}
//				}
//				if(dy!=b){
//					if(dy>b){
//						dy-=speed/50.0;
//					}
//					else{
//						dy+=speed/50.0;
//					}
//				}
			dx=a;
			dy=b;
		}
		range--;
		x+=dx;
		y+=dy;
		
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, size, size);
		if(range<=450){
			g.setColor(Color.orange);
			g.drawLine((int)x,(int)y,(int)(x-size*dx),(int)(y-size*dy));
			g.drawLine((int)x,(int)y,(int)(x-size*dx),(int)(y-size*dy));
		}
		update();
	}
}
