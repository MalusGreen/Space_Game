import javax.swing.*;


public class GameFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Game game;
	
	public static void main(String args[]){
		new GameFrame("Space Game").setVisible(true);
	}
	public GameFrame(String name){
		super(name);
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		game=new Game();
		add(game);
		
		addKeyListener(game);
	}
}
