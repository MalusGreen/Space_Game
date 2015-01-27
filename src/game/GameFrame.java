package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GameFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Timer timer;
	static Container c;
	static Game game;
	static Menu menu;
	static CardLayout cards;
	static Color[] colors=new Color[]{Color.red,Color.orange,Color.cyan,Color.green,Color.yellow};
	public static void main(String args[]){
		new GameFrame("Space Game").setVisible(true);
	}
	public GameFrame(String name){
		super(name);
		setSize(1000, 800);
		setResizable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		cards=new CardLayout();
		c=this.getContentPane();
		setLayout(cards);

		menu=new Menu();
		add(menu);
		
		game=new Game();
		add(game);
		
		//Menu
		for(int i=0;i<menu.colors.length;i++){
			menu.colors[i].addActionListener(this);
		}
		
		
		c.add(menu, "Show Menu");
		c.add(game, "Show Game");
		
		addKeyListener(game);
		timer=new Timer(10,this);
		timer.addActionListener(this);
		timer.start();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<menu.colors.length;i++){
			if(e.getSource()==menu.colors[i]){
				cards.show(c, "Show Game");
				game.requestFocus();
				game.setColor(colors[i]);
				game.getTimer().start();
			}
		}
		if(e.getSource()==timer){
			repaint();
		}
		else{
			timer.stop();
		}
	}
}
