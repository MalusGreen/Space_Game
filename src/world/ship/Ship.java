package world.ship;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import world.World;
import world.ship.weapons.*;
import world.ship.weapons.ammo.Ammo;



/* Ship class is the basic class 
 * of which different ship types can be.
 * 
 * Depending on customization Ship can have different weapons
 * from the weapons class, which will shoot different types
 * of ammo.
 * 
 * shoot()  -fires weapons
 * turn()   -turns the ship
 * accel()  -accelerates the ship in the direction of var angl
 * damage() -TODO damage calc
 * draw()   -draws ship and ship components.
 * 			-TODO rotation
 * update() -updates position of ship
 * 			-updates status of ship
 * 				-alive
 */

public class Ship {
	protected double angle;
	protected double dx,dy;
	protected double speed;
	protected double x, y;
	protected int size;
	
	//TODO The only purpose of this variable is to draw Engines. I think I should change this.
	protected int accel;
	
	protected int MAXHEALTH;
	protected int health;
	protected int team;
	protected boolean alive;
	
	//TODO change this mechanic.
	protected int weapon;
//	protected Ship target;
//	protected ArrayList<Terrain> map;

	protected ArrayList<Weapon> weapons=new ArrayList<Weapon>();
	
	public Ship(double x, double y){
		initVars(x,y);
	}
	
	public Ship(double x, double y, String player){
		this(x,y);
		//Player Attributes
		team=0;
		
		size=3;
		speed=3;
		
		MAXHEALTH=100;
		health=MAXHEALTH;
		
		weapons.add(new MachineGun());
		weapons.add(new MissleLauncher());
		weapons.add(new Spitter());
	}
	
	private void initVars(double x, double y){
		angle=0;
		this.x=x;
		this.y=y;
		dx=0;
		dy=0;
		accel=0;
		alive=true;
	}
	
	public void setWorld(){
		
	}
	//Targets closest Enemy
	
	public void draw(Graphics g){
//		Attempts at rotation. 
//		Graphics2D g2d=(Graphics2D)g;
//		g2d.setColor(Color.red);
//		Rectangle rect1=new Rectangle((int)x-size,(int)y-size,size*2,size*2);
//		g2d.rotate(angle);
//		g2d.draw(rect1);
//		g2d.translate(rect1.x+(rect1.width/2), rect1.y+(rect1.height/2));
		

		// TODO Auto-generated method stub
		if(alive){
			drawShip(g);
		}
	}
//	public void update(ArrayList<Enemy> enemies, ArrayList<Terrain> map){
	public void update(){
		x+=dx;
		y+=dy;
		dx*=0.99;
		dy*=0.99;
		if(health<=0){
			alive=false;
		}
		for(Weapon i: weapons){
			//TODO The idea that there will be auto-locking weapons in the future needs to be considered.
			i.update();
		}
	}
	
	//Draw all the modules on your ship.
	public void drawShip(Graphics g){
		drawWeapons(g);
		drawEngine(g);
		drawBody(g);
		drawHealth(g);
	}
	protected void drawWeapons(Graphics g){
		weapons.get(weapon).draw(g,x,y,angle);
	}
	protected void drawEngine(Graphics g){
		if(accel>0){
			g.setColor(Color.orange);
			g.drawLine((int)x,(int)y,(int)(x-2*size*Math.cos(angle+0.3)),(int)(y+2*size*Math.sin(angle+0.3)));
			g.drawLine((int)x,(int)y,(int)(x-2*size*Math.cos(angle-0.3)),(int)(y+2*size*Math.sin(angle-0.3)));
			accel--;
		}
	}
	protected void drawBody(Graphics g){
		g.setColor(Color.red);
		g.drawRect((int)x-size,(int)y-size,size*2,size*2);
		
	}
	protected void drawHealth(Graphics g){
		g.setColor(Color.red);
		g.drawLine((int)(x-size*1.5), (int)y-6, (int)(x+size*1.5), (int)y-6);
		g.setColor(Color.green);
		g.drawLine((int)(x-size*1.5), (int)y-6, (int)((x-size*1.5)+3*size*health/MAXHEALTH), (int)y-6);
	}
	
	//Accelerates in the var angle direction.
	public void accel(){
		accel=2;
		dx+=Math.cos(angle)/10;
		dy-=Math.sin(angle)/10;
		if(dx*dx+dy*dy>speed*speed){
			dy+=Math.sin(angle)/10;
			dx-=Math.cos(angle)/10;
		}
	}
	public void deccel(){
		dx*=0.9;
		dy*=0.9;
	}
	public void turn(double turn_rate){
		angle+=turn_rate;
	}
	public void switchWeapon(int weapon){
		this.weapon=weapon;
	}
	public void shoot(){
		//TODO make arraylist of ammo so ammo can be sent from multiple weapons.
		Ammo a=weapons.get(weapon).fire(x,y,angle);
		if(a!=null){
			a.setTeam(team);
			World.getBullets().add(a);
		}
	}
	
	public void crash(){
		System.out.println("Ship: Temp Crash");
		dx*=-0.5;
		dy*=-0.5;
		x+=dx*2;
		y+=dy*2;
		health-=10;
	}
	
	//Getters and Setters
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public void setHealth(int health){
		this.health=health;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isAlive() {
		return alive;
	}
	public int getTeam(){
		return team;
	}
	public int getHealth(){
		return health;
	}
	
	public double getSize() {
		return size;
	}

	public double getDx() {
		return dx;
	}
	public double getDy() {
		return dy;
	}
	public double getSpeed() {
		return speed;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public ArrayList<Weapon> getWeapons(){
		return weapons;
	}
	public Rectangle getRect(){
		return new Rectangle((int)x-size,(int)y-size,size*2,size*2);
	}
}
