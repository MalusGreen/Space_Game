package world.ship;
import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.weapons.ammo.Ammo;

public abstract class Enemy extends Ship{
	/* Enemy has all the functions of Ship, 
	 * the only difference that behaviors are 
	 * programmed into enemies.
	 * 
	 * target- for user co-ordinates
	 * avoid - for avoiding bullets
	 * damage- for checking bullet and self collision
	 * draw  - differs for each Enemy
	 * 
	 * RingWraith:
	 * 		target() 
	 * 			finds the user 
	 * 			and moves towards him or her.
	 * 		damage()
	 * 			If hit by bullet lose a health... dies.
	 * 			removes bullet.
	 * 			If hits user, loses health, dies, 
	 * 			and damages user.
	 * 		draw()
	 * 			Draws a circle most of the time.
	 * 			Death animation is a spreading square.
	 */
	public double tx, ty;
	public static int right;
	public Enemy(double x, double y){
		super(x,y);
	}
	public void update(Ship user, ArrayList<Ammo> ammo){
		// TODO Auto-generated method stub
		//Movement
		target(user);
		ArrayList<Ship> temp=new ArrayList<Ship>();
		temp.add(user);
		super.update(temp);
		getCollide(ammo);
	}
	@Override
	public abstract void drawShip(Graphics g);
	public abstract void target(Ship user);
	public abstract void avoid(Ship user);
}
