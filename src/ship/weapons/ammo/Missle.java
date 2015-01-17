package ship.weapons.ammo;

import java.awt.Color;
import java.awt.Graphics;

public class Missle extends Ammo{
	public Missle(double x, double y, double angle){
		super(x,y,angle);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		tx=target.getX();
		ty=target.getY();
		double a=tx-x, b=ty-y;
		double hyp=Math.sqrt(a*a+b*b);
		a=a*speed/hyp;
		b=b*speed/hyp;
		if(dx!=a){
			if(dx>a){
				dx-=speed/50.0;
			}
			else{
				dx+=speed/50.0;
			}
		}
		if(dy!=b){
			if(dy>b){
				dy-=speed/50.0;
			}
			else{
				dy+=speed/50.0;
			}
		}
		dy=b*speed/hyp;
		x+=dx;
		y+=dy;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, 2, 2);
		g.setColor(Color.orange);
		g.drawLine((int)x,(int)y,(int)(x-2*size*Math.cos(angle+0.3)),(int)(y+2*size*Math.sin(angle+0.3)));
		g.drawLine((int)x,(int)y,(int)(x-2*size*Math.cos(angle-0.3)),(int)(y+2*size*Math.sin(angle-0.3)));
	}
}
