package world.terrain;

import java.awt.Color;
import java.awt.Graphics;

import world.ship.BigWraith;
import world.ship.Enemy;
import world.ship.RingWraith;

public class Spawner extends Terrain{
	Enemy e;
	public Spawner(int type, double x, double y){
		this.x=x;
		this.y=y;
		this.size=10;
		switch(type){
		case 1:
			e=new RingWraith(x,y);
			break;
		case 2:
			e=new BigWraith(x,y);
			break;
		default:
			e=new BigWraith(x,y);
		}
		//it's k
		//or at least I thinK
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255,25,255));
		g.drawOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(new Color(255,25,255,50));
		g.fillOval((int)x-size, (int)y-size, size*2, size*2);
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
