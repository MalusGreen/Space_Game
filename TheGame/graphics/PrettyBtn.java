package graphics;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;



public class PrettyBtn extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color c1,c2;
	Grid grid;
	public PrettyBtn(String title,int type, Color c) {
		// TODO Auto-generated constructor stub
		this();
		if(type==1){
			c1 = c;
			c2 = c.darker();
		}
		else if(type==2){
			c1 = c.darker();
			c2 = c;
		}
		setText(title);
		setFont(DefFont.derived(12,0.4));
		setSize(120, 30);
		setBackground(c1);
		setForeground(c2);
		addMouseListener(this);
		setContentAreaFilled(true);
	}
	public PrettyBtn(){
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setOpaque(true);
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	public PrettyBtn(ImageIcon icon, ImageIcon hover){
		this();
		setIcon(icon);
		setBackground(c2);
		this.setRolloverEnabled(true);
		this.setRolloverIcon(hover);
	}
	
	public void setGrid(Grid grid){
		this.grid=grid;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

		if (e.getSource() == this) {
			this.setBackground(c2);
			this.setForeground(c1);
			grid.setColor(c1);
		}
		
	}

	public void mouseExited(MouseEvent e) {

		if (e.getSource() == this) {
			this.setBackground(c1);
			this.setForeground(c2);
		}
	}
}