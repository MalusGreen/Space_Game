import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GameFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		cards.show(c, "Show Game");
		game.requestFocus();
		for(int i=0;i<menu.colors.length;i++){
			if(e.getSource()==menu.colors[i]){
				game.setColor(colors[i]);
			}
		}
	}
}
