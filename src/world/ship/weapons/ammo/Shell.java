package world.ship.weapons.ammo;

import java.awt.Color;
import java.awt.Graphics;

import world.World;

public class Shell extends Ammo{
	public Shell(double x, double y, double angle){
		super(x, y, angle, 4);
		size=6;
		range=50;
		damage=5;
	}
	@Override
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.drawRect((int)x, (int)y, size, size);
		update();
	}

	@Override
	public void update(){
		x+=dx;
		y+=dy;
		range--;
		if(range==0){
			for(int i=0;i<60;i++){
				World.getBullets().add(new Bullet(x, y, angle+Math.PI*2*i/60));
			}
		}
	}
}
