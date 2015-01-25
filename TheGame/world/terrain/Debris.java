package world.terrain;

import java.awt.Color;
import java.awt.Graphics;

public class Debris extends Terrain{
	public Debris(double x, double y, int size){
		this.x=x;
		this.y=y;
		this.dx=Math.random()+1;
		this.dy=Math.random()+1;
		this.size=Math.random()*size;
		this.color=new int[]{0,255,0};
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(color[0],color[1], color[2]));
		g.drawRect((int)x,(int)y,(int)size,(int)size);
		g.setColor(new Color(color[0],color[1], color[2],100));
		g.fillRect((int)x,(int)y,(int)size,(int)size);
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
