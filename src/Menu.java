import java.awt.*;

import graphics.PrettyBtn;

import javax.swing.*;


public class Menu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton[] colors;
	public String[] color=new String[]{"Red","Orange","Blue","Green","Yellow"};
	public Menu(){
		super();
		setSize(1000,800);
		setLayout(new GridLayout());
		colors=new JButton[5];
		for(int i=0;i<colors.length;i++){
			colors[i]=new PrettyBtn(color[i],2, GameFrame.colors[i]);
//			colors[i]=new JButton();
			add(colors[i]);
		}
	}
}
