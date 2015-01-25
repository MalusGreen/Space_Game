package world;

import java.awt.Graphics;
import java.util.ArrayList;

import world.terrain.Terrain;

public class Map {
	ArrayList<Terrain> terrain;
	public Map(){
		terrain=new ArrayList<Terrain>();
	}
	public void draw(Graphics g){
		for(Terrain t:terrain){
			t.draw(g);
			t.update();
		}
	}
}