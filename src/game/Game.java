package game;

import graphics.Grid;
import graphics.Pixel;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import world.World;
import world.ship.*;
import world.ship.weapons.ammo.*;
import world.terrain.Debris;
import world.terrain.Terrain;


public class Game extends JPanel implements KeyListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pixel[] pixels;
	private Pixel[] pixels_2;
	private ArrayList<Ship> enemies;
	private ArrayList<Ammo> projectiles;
	private ArrayList<Terrain> terrain;
	
	private World galaxy;
	private Grid grid;
	private Ship ship;
	private Camera cam;
	
	private boolean left, right, up, down, space, tab;
	private JLabel FPS;
	private long start, end, elapsed;
	
	//So we have a bit more fun testing it.
	private int difficulty;
//	Action up, right, left;
	
	private Timer timer;
	
	
	public enum GameState{
		INBATTLE, GAMEOVER;
	}
	
	public Game() throws FileNotFoundException, IOException{
		super();
		setSize(1000,800);
		
		initVar();
		initGraphics();
		initWorld("World_2.txt");
		initMisc();
		
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
	}
	
	private void initVar(){
		difficulty=1;
		timer=new Timer(10,this);
		ship=new Ship(100,100,"Player");
	}
	
	private void initGraphics(){
		setBackground(new Color(0, 0, 0));
		setBackground(50);
	}
	
	private void initWorld(String path) throws FileNotFoundException, IOException{
		galaxy=new World();
		galaxy.readWorld(path);
		terrain=World.getTerrain();
		enemies=World.getEnemy();
		projectiles=World.getBullets();
	}
	
	private void initMisc(){
		//FPS show.
		FPS=new JLabel();
		FPS.setForeground(Color.white);
		add(FPS);
		
		//Camera
		cam=new Camera();
	}
		
//	ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		//FPS Timer
//		System.out.println("Game.actionPerformed(): "+ship.getHealth());
		if(e.getSource()==timer){
			
			end=System.nanoTime();
			elapsed++;
			if(end!=0&&elapsed==10){
				elapsed=0;
				FPS.setText("System: "+World.getSystem().getSystem()+"   FPS: "+1000000000/((end-start)/10));
				start=end;
			}
			//Keypressed stuff.
			updateKeys();
			ship.update();
			cam.center((int)ship.getX(), (int)ship.getY());
	//		Checks collision and removes bullets.
			updateBullets();
			updateCollision();
			updateTerrain();
			updateEnemies();
		}
		repaint();
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W){
			up=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_D){
			right=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_A){
			left=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_S){
			down=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			up=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left=true;
		}
		else if(e.getKeyCode()==32){
			space=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_1){
			ship.switchWeapon(0);
		}
		else if(e.getKeyCode()==KeyEvent.VK_2){
			ship.switchWeapon(1);
		}
		else if(e.getKeyCode()==KeyEvent.VK_3){
			ship.switchWeapon(2);
		}
		else if(e.getKeyCode()==KeyEvent.VK_TAB){
			tab=true;
		}
		else if(e.getKeyCode()==KeyEvent.VK_E){
			timer.stop();
			ship.setHealth(100);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W){
			up=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_D){
			right=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_A){
			left=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_S){
			down=false;
			//TODO Actual Camera and sector panning, then enemies, spawners and 
			galaxy.nextSystem();
			terrain=World.getTerrain();
			enemies=World.getEnemy();
			projectiles=World.getBullets();
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP){
			up=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			right=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			left=false;
		}
		else if(e.getKeyCode()==32){
			space=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_TAB){
			tab=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_E){
			timer.start();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	//	KeyListener
//	KeyCode
	private void updateKeys(){
		if(up){
			ship.accel();
		}
		if(right){
			ship.turn(Math.toRadians(-4));
		}
		if(left){
			ship.turn(Math.toRadians(4));
		}
		if(down){
			ship.deccel();
		}
		if(space){
			ship.shoot();
		}
	}

	//	Removes bullets out of stuff.
	private void updateBullets(){	
		for(int i=0;i<projectiles.size();i++){
			//TODO Map Scrolling
			if(projectiles.get(i).getRange()<=0){
				projectiles.remove(i);
				i--;
			}
			//TODO Not complete yet, make this bit of code more generalized.
			else if(projectiles.get(i).getTeam()!=0){
				ArrayList<Ship> temp=new ArrayList<Ship>();
				temp.add(ship);
				projectiles.get(i).lockOn(temp);
			}
			else if(projectiles.get(i).getTeam()==0){
				projectiles.get(i).lockOn(enemies);
			}
		}
	}

	private void updateCollision(){
		for(Terrain t:terrain){
			if(t.getRect().intersects(ship.getRect())){
				t.setDXY(ship.getDx()*ship.getSize(), ship.getDy()*ship.getSize());
				ship.crash();
				t.setHealth(t.getHealth()-10);
			}
			for(Ship e: enemies){
				if(t.getRect().intersects(e.getRect())){
					t.setHealth(t.getHealth()-e.getHealth());
					e.setAlive(false);
				}
			}
			for(Ammo a:projectiles){
				if(t.getRect().intersects(a.getRect())){
					t.setHealth(t.getHealth()-a.getDamage());
					a.setRange(0);
				}
			}
		}
		for(Ammo a:projectiles){
			if(a.getRect().intersects(ship.getRect())&&a.getTeam()!=ship.getTeam()){
				ship.setHealth(ship.getHealth()-a.getDamage());
				a.setRange(0);
			}
		}
		for(Ship e: enemies){
			for(Ammo a:projectiles){
				if(a.getRect().intersects(e.getRect())&&a.getTeam()!=e.getTeam()){
					e.setHealth(e.getHealth()-a.getDamage());
					a.setRange(0);
				}
			}
			if(ship.getRect().intersects(e.getRect())){
				ship.setHealth(ship.getHealth()-20);
				e.setAlive(false);
			}
		}
	}
	
	private void updateEnemies(){
		for(int i=0;i<enemies.size();i++){
			Enemy e=(Enemy) enemies.get(i);
//			if(e.getHealth()<=-10){
//				enemies.remove(e);
//				i--;
//				continue;
//			}
			if(e.isAlive()){
				e.update(ship);
			}
			else{
				enemies.remove(e);
				i--;
			}
		}
	}
	
	private void updateTerrain(){
		for(int i=0;i<terrain.size();i++){
			if(terrain.get(i).getHealth()<=0){
				terrain.remove(i);
				i--;
				continue;
			}
			terrain.get(i).update();
		}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.translate(cam.x/2,cam.y/2);
		drawBackground(g);
		g.translate(cam.x/2,cam.y/2);
		drawEnemies(g);
		drawBullets(g);
		drawTerrain(g);
		ship.draw(g);
		g.translate(-cam.x, -cam.y);
	}

	
	//Draws bullets
	private void drawBullets(Graphics g){
		for(int i=0;i<projectiles.size();i++){
			projectiles.get(i).draw(g);
		}
	}
	
	
	//Draws and updates
	private void drawEnemies(Graphics g){
		for(Ship s: enemies){
			Enemy e=(Enemy) s;
			e.drawShip(g);
		}
	}
	
	private void drawTerrain(Graphics g){
		for(Terrain t: terrain){
			t.draw(g);
			if(tab){
				t.drawHealth(g);
			}
		}
	}
	
	
	//Draws Backgrounds based on Pixels
	private void drawBackground(Graphics g){
		for(int i=0;i<pixels.length;i++){
			pixels[i].draw(g, 0, 0);
			pixels_2[i].draw(g, 0, 0);
		}
		grid.draw(g,0,0);
	}
	
	
	//Generates Pixels;
	public void setBackground(int size) {
		pixels = new Pixel[size];
		pixels_2 = new Pixel[size];
		grid=new Grid(new Color(255,255,0),getWidth()/100,getHeight()/100, 100);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 
					new Pixel(
					new Color(200, 200, 200, 100), 
					1000, 
					800);
			pixels_2[i] = 
					new Pixel(
					new Color(200, 200, 255, 100), 
					1000, 
					800);
		}
	}
	
	public void setColor(Color color){
		grid.setColor(color);
	}
	public Timer getTimer(){
		return timer;
	}
}
