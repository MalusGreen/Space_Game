package game;

import java.awt.*;
import graphics.Grid;
import graphics.PrettyBtn;

import javax.swing.*;


public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public PrettyBtn[] colors;
	public String[] color=new String[]{"Red","Orange","Blue","Green","Yellow"};
	public Grid grid;
	public Menu(){
		//TODO LOL CHANGE THIS AFTER YOU CHECK IT
		super();
		
		this.grid=new Grid(Color.gray, 1000/100,800/100, 100);
		
		setSize(1000,800);
		setLayout(new GridBagLayout());
		setBackground(Color.white);
		
		{
			colors=new PrettyBtn[5];
			for(int i=0;i<colors.length;i++){
				colors[i]=new PrettyBtn(color[i], 2, GameFrame.colors[i]);
				colors[i].setGrid(this.grid);
				add(colors[i]);
			}
		}
	}
	@Override
	public void paintComponent(Graphics g ){
		super.paintComponent(g);
		grid.draw(g, 0, 0);
	}
}
