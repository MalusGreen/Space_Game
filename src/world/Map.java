package world;

import java.awt.Graphics;
import java.util.ArrayList;

import world.ship.Enemy;
import world.ship.Ship;
import world.ship.weapons.ammo.Ammo;
import world.terrain.Terrain;

public class Map {
	ArrayList<Terrain> terrain;
	ArrayList<Ship> enemies;
	ArrayList<Ammo> bullets;
	String system;
	public Map(String name){
		system=name;
		terrain=new ArrayList<Terrain>();
		enemies=new ArrayList<Ship>();
		bullets=new ArrayList<Ammo>();
	}
	
	public String getSystem(){
		return system;
	}
	
	public void addTerrain(Terrain t){
		terrain.add(t);
	}
	
	public void draw(Graphics g){
		for(Terrain t:terrain){
			t.draw(g);
		}
	}
}