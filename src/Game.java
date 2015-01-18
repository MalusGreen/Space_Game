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
	private boolean left, right, up, space;
	
//	Action up, right, left;
	
	Timer timer;
	public Game(){
		super();
		setSize(1000,800);
		timer=new Timer(10,this);
		
		ship=new Ship();
		enemies=new ArrayList<Ship>();
		setBackground(new Color(0, 0, 0));
		setBackground(50);
		
		addRingShip(1);
		addKeyListener(this);
		
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
			//enemies.add(new RingWraith(Math.random()*getWidth()+200,Math.random()*getHeight()+500));
			enemies.add(new RingWraith(200,500));
		}
	}
	
	//Draws and updates
	public void drawEnemies(Graphics g){
		
		//Test Method... adds RingWraiths
		if(enemies.isEmpty()){
			addRingShip(10);
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
		grid=new Grid(new Color(0,255,0),getWidth()/100,getHeight()/100, 100);
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
	
	public ArrayList<Ship> getEnemies(){
		return enemies;
	}
	//ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
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
			ship.turn(Math.toRadians(-5));
		}
		if(left){
			ship.turn(Math.toRadians(5));
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
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W){
			System.out.println("left");
			up=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_D){
			System.out.println("left");
			right=false;
		}
		else if(e.getKeyCode()==KeyEvent.VK_A){
			System.out.println("left");
			left=false;
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
