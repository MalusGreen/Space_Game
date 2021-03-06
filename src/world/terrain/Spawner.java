package world.terrain;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Constructor;

import world.World;
import world.ship.BigWraith;
import world.ship.Enemy;
import world.ship.RingWraith;

public class Spawner extends Terrain{
	Enemy e;
	int type;
	int counter;
	public Spawner(int type, double x, double y){
		this.x=x;
		this.y=y;
		this.size=8;
		this.health=20;
		this.MAXHEALTH=20;
		this.type=type;
		counter=500;
//		switch(type){
//		case 1:
//			e=new RingWraith(x,y);
//			break;
//		case 2:
//			e=new BigWraith(x,y);
//			break;
//		default:
//			e=new BigWraith(x,y);
//		}
	}
	@Override
	public void draw(Graphics g) {
		if(type==1)
			g.setColor(new Color(255,0,255));
		else
			g.setColor(new Color(0,255,255));
		
		g.drawOval((int)x-size, (int)y-size, size*2, size*2);
		g.setColor(new Color(255,25,255,50));
		g.fillOval((int)x-size, (int)y-size, size*2, size*2);
	}
	@Override
	public void update() {
		counter++;
		if(counter<500){
			return;
		}
		if(type==1){
			World.getEnemy().add(new RingWraith(x,y+25));
		}
		else{
			World.getEnemy().add(new BigWraith(x,y+25));
		}
		counter=0;
	}
}
