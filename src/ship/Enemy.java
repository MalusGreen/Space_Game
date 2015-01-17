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
		affectSpeed();
		super.update();
		
	}
	
	private void affectSpeed(){
		double a=tx-x, b=ty-y;
		double hyp=Math.sqrt(a*a+b*b);
		double ddx=a*speed/hyp;
		double ddy=b*speed/hyp;
		System.out.println("sides: "+a+","+b);
		
		double tangle = Math.atan(ddx/ddy);
		double dangle = angle-tangle;
		System.out.println("fucking angle is "+tangle);
		
		if (dangle>Math.PI) dangle-=2*Math.PI;
		if (dangle<-Math.PI)dangle+=2*Math.PI;
		
		boolean ypos = ddx>0;
		boolean xpos = ddy>0;
		
		if ((ypos && !xpos) || (!ypos&& xpos)){
			dangle+=Math.PI;
		}
		
		
		
		System.out.println("dangle:"+dangle);
		System.out.println("angle: "+angle);
		System.out.println("dx: "+ddx);
		System.out.println("dy: "+ddy);
		
		boolean up = true, right = (dangle>0), left = (dangle<0);
		
		
		if(right){
			turn(Math.toRadians(-2));
			System.out.println("r");
		}
		if(left){
			System.out.println("l");
			turn(Math.toRadians(2));
		}
		if(up){
			accel();
		}
		
		System.out.println(dx+","+dy);
		
		
		
	}
	
	@Override
	public abstract void drawShip(Graphics g);
	public abstract void target(Ship user);
	public abstract void avoid(Ship user);
	public abstract void damage(Ship user);
}
