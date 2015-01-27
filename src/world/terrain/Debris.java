package world.terrain;

import java.awt.Color;
import java.awt.Graphics;

public class Debris extends Terrain{
	public Debris(double x, double y, int size){
		this.x=x;
		this.y=y;
		this.dx=Math.random()*2-1;
		this.dy=Math.random()*2-1;
		

		this.size=(int)(Math.random()*size)+5;
		this.MAXHEALTH=20*this.size;
		this.health=MAXHEALTH;
		this.color=new int[]{0,255,0};
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(color[0],color[1], color[2]));
		g.drawRect((int)(x-size),(int)(y-size),(int)size*2,(int)size*2);
		g.setColor(new Color(color[0],color[1], color[2],100));
		g.fillRect((int)(x-size),(int)(y-size),(int)size*2,(int)size*2);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		x+=dx;
		y+=dy;
		dx*=0.99;
		dy*=0.99;
	}
}
