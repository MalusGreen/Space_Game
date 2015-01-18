package graphics;

import java.awt.Color;
import java.awt.Graphics;

public class Grid {
	float[] color;
	int x,y;
	int size;
	float[][] opacity;
	public Grid(Color c, int x, int y, int size){
		color=c.getRGBColorComponents(null);
		this.x=x+1;
		this.y=y+1;
		this.size=size;
		randomize();
	}
	
	public void draw(Graphics g, int cx, int cy){
		drawGrid(g,cx,cy);
		fillGrid(g,cx,cy);
	}
	
	public void drawGrid(Graphics g, int cx, int cy){
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
//				g.setColor(new Color(color[0],color[1],color[2],opacity[i][j]));
				g.setColor(new Color(color[0],color[1],color[2],0.2f));
				g.drawRect(i*size, j*size, size, size);
			}
		}
	}
	
	public void fillGrid(Graphics g, int cx, int cy){
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				g.setColor(new Color(color[0],color[1],color[2],opacity[i][j]));
				g.fillRect(i*size, j*size, size, size);
			}
		}
	}
	
	public void randomize(){
		opacity=new float[x][y];
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				opacity[i][j]=(float) Math.random()/4+0.05f;
			}
		}
	}
}
