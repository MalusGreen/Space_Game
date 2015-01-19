package world.terrain;

import java.awt.Graphics;

public class Debris extends Terrain{
	public Debris(double x, double y, int size){
			x=x;
		super.y=y;
		super.size=Math.random()*size;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
}
