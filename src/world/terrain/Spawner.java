package world.terrain;

import java.awt.Graphics;

import world.ship.BigWraith;
import world.ship.Enemy;
import world.ship.RingWraith;

public class Spawner extends Terrain{
	Enemy e;
	public Spawner(int type, double x, double y){
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
