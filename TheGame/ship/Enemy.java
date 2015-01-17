package ship;
import java.awt.Graphics;

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
	public Enemy(double x, double y){
		super();
		super.x=x;
		super.y=y;
	}
	public void update(Ship user){
		// TODO Auto-generated method stub
		//Movement
		target(user);
		damage(user);
		super.update();
		
	}
	@Override
	public abstract void drawShip(Graphics g);
	public abstract void target(Ship user);
	public abstract void avoid(Ship user);
	public abstract void damage(Ship user);
}
