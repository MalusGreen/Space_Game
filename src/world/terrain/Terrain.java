package world.terrain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Terrain {
	protected int MAXHEALTH;
	protected int health;
	protected double x,y;
	protected double dx,dy;
	protected int size;
	protected int[] color;
	
	protected String type;
	protected String location;
	protected String id;
	
	public Terrain(){
		
	}
	public abstract void draw(Graphics g);
	public abstract void update();
	public void drawHealth(Graphics g){
		g.setColor(Color.green);
		g.drawLine((int)(x-size*1.5), (int)y-size-5, (int)(x+size*(3.0*health/MAXHEALTH-1.5)), (int)y-size-5);
		if((int)(x+size*(3.0*health/MAXHEALTH-1.5))>1000){
			System.out.println("New Entry");
			System.out.println("Terrain.drawHealth(): X"+x);
			System.out.println("Terrain.drawHealth(): Size"+size);
			System.out.println("Terrain.drawHealth(): health"+health);
			System.out.println("Terrain.drawHealth(): MAXHEALTH"+MAXHEALTH);
		}
	}
	public void setType(String TYPE){
		type=TYPE;
	}
	public void setLocation(String LOCATION){
		location=LOCATION;
	}
	public void setID(String ID){
		id=ID;
	}
	public Rectangle getRect(){
		return new Rectangle((int)(x-size),(int)(y-size),(int)size*2,(int)size*2);
	}
	public void setHealth(int health){
		this.health=health;
	}
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}
}
