import graphics.Grid;
import graphics.Pixel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import world.ship.*;


public class Game extends JPanel implements KeyListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pixel[] pixels;
	private Pixel[] pixels_2;
	private ArrayList<Ship> enemies;
	private Grid grid;
	private Ship ship;
	private boolean left, right, up, down, space;
	private JLabel FPS;
	private long start, end;
//	Action up, right, left;
	
	Timer timer;
	public Game(){
		super();
		setSize(1000,800);
		timer=new Timer(10,this);
		ship=new Ship(100,100);
		enemies=new ArrayList<Ship>();
		setBackground(new Color(0, 0, 0));
		setBackground(50);
		
		addRingShip(10);
		addBigShip(5);
		
		
		addKeyListener(this);
		FPS=new JLabel();
		FPS.setForeground(Color.white);
		if(start!=0&&end!=0)
			FPS.setText("FPS: "+1/((start-end)/1000000000.0));
		add(FPS);
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawBackground(g);
		drawEnemies(g);
		ship.draw(g);
		ship.update(enemies);
	}
	
	//Generates Enemies
	public void addRingShip(int n){
		for(int i=0;i<n;i++){
			enemies.add(new RingWraith(Math.random()*getWidth()+200,Math.random()*getHeight()+500));
//			enemies.add(new RingWraith(200,500));
		}
	}	
	public void addBigShip(int n){
		for(int i=0;i<n;i++){
			enemies.add(new BigWraith(Math.random()*getWidth()+200,Math.random()*getHeight()+500));
//			enemies.add(new RingWraith(200,500));
		}
	}
	
	//Draws and updates
	public void drawEnemies(Graphics g){
		
		//Test Method... adds RingWraiths
		if(enemies.isEmpty()){
			addRingShip(10);
			addBigShip(5);
		}
		else{
			// TODO Auto-generated method stub
			//Try to condense this all to one method.
			for(int i=0;i<enemies.size();i++){
				//Change to alive boolean.
				Enemy e=(Enemy) enemies.get(i);
				if(e.getHealth()<=-10){
					enemies.remove(e);
					i--;
					continue;
				}
				//Change to become one method
				else if(e.isAlive()){
					e.update(ship);
				}
				e.drawShip(g);
			}
		}
	}
	
	//Draws Backgrounds based on Pixels
	public void drawBackground(Graphics g){
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
	
	public ArrayList<Ship> getEnemies(){
		return enemies;
	}
	//ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		start=end;
		end=System.nanoTime();
		if(start!=0&&end!=0)
			FPS.setText("FPS: "+1000000000/((end-start)));
		keys();
		repaint();
	}
	
	//KeyListener
	//KeyCode
	public void keys(){
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
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
