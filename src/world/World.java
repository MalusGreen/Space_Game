package world;

import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;

import world.ship.Enemy;
import world.ship.Ship;
import world.ship.weapons.ammo.Ammo;
import world.terrain.Debris;
import world.terrain.Spawner;
import world.terrain.Terrain;

public class World {
	Map[] sectors;
	int system;
	public static ArrayList<Terrain> terrain;
	public static ArrayList<Ship> enemies;
	public static ArrayList<Ammo> bullets;
	public World(){
		system=0;
		terrain=new ArrayList<Terrain>();
		enemies=new ArrayList<Ship>();
		bullets=new ArrayList<Ammo>();
	}
	public void draw(Graphics g){
		sectors[system].draw(g);
	}
	public void nextSystem(){
		system++;
		system%=sectors.length;
		changeSystem(system);
	}
	public void changeSystem(int c){
		system=c;
		terrain=sectors[system].terrain;
		enemies=sectors[system].enemies;
		bullets=sectors[system].bullets;
	}
	
	public void createWorld() throws IOException{
//		BufferedWriter writer = null;
//		BufferedReader reader = null;
		String fileName="World_"+saveNum()+".txt";
		try(BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
				BufferedReader reader=new BufferedReader(new FileReader("WorldGen.txt"))){
			writer.write(newWorld(reader));
		}
	}
	
	public void readWorld(String path) throws FileNotFoundException, IOException{
		try(BufferedReader reader=new BufferedReader(new FileReader(path))){
			String input, cluster;
			String input_list[];
			int size, num;
			Terrain item;
			
			input=reader.readLine();
			sectors=new Map[Integer.parseInt(input.substring(input.indexOf(":")+1, input.indexOf("]")))];
			for(int i=0;i<sectors.length;i++){
				
				//Creating System.
				input=reader.readLine();
				sectors[i]=new Map(input.substring(input.indexOf(":")+1, input.indexOf("]")));
				
				//Creating Debris.
				input=reader.readLine();
				input_list=input.substring(input.indexOf(":")+1, input.indexOf("]")).split(":");
				num=Integer.parseInt(input_list[0]);
				size=Integer.parseInt(input_list[1]);
				
				input=reader.readLine();
				while(input.substring(input.indexOf("[")+1,input.indexOf(":")).equals("CLUSTER")){
//					cluster=input.substring(input.indexOf);

					//Debris
					for(int t=0;t<num;t++){
						
						//Input
						input=reader.readLine();
						input_list=input.split(" ");
						
						//Gen
						item=new Debris(Integer.parseInt(input_list[0]),Integer.parseInt(input_list[1]),size);
						item.setType("CLUSTER");
						item.setLocation(sectors[i].getSystem().charAt(0)+input_list[0]+sectors[i].getSystem().charAt(0)+input_list[1]);
						item.setID("X:"+input_list[0]+" Y:"+input_list[1]);
						sectors[i].addTerrain(item);
					}
					input=reader.readLine();
				}
				//Spawners
				input=input.substring(input.indexOf(":")+1,input.indexOf("]"));
				num=Integer.parseInt(input);
				
				for(int s=0;s<num;s++){
					//Input
					input=reader.readLine();
					input_list=input.split(" ");
					size=(int)(Math.round(Math.random()*1.5));
					//Gen
					item=new Spawner(size,Integer.parseInt(input_list[0]),Integer.parseInt(input_list[1]));
					item.setType("SPAWNER");
					item.setLocation(sectors[i].getSystem().charAt(0)+input_list[0]+sectors[i].getSystem().charAt(0)+input_list[1]);
					item.setID("TYPE: "+size);
					sectors[i].addTerrain(item);
				}
			}
		}
		changeSystem(0);
	}
	
	private int saveNum(){
		File saveFile = new File("World_1.txt");
		int saveNum=1;
		while(saveFile.exists()){
			saveNum++;
			saveFile= new File("World_"+saveNum+".txt");
		}
		return saveNum;
	}
	
	private String newWorld(BufferedReader reader) throws IOException{
		String data="";
		
		int worldSize, debrisDensity, difficulty;
		
		//Recommend Hashtable or sets instead of methods.
		{
			String input;
			
			input			=reader.readLine();
			worldSize		=readSize(input.substring(input.indexOf(":")+1,input.indexOf("]")));
			input			=reader.readLine();
			debrisDensity	=readNum(input.substring(input.indexOf(":")+1,input.indexOf("]")));
			input			=reader.readLine();
			difficulty		=readNum(input.substring(input.indexOf(":")+1,input.indexOf("]")));
		}
		worldSize*=(Math.random()*6+1+6*(worldSize-1));
		debrisDensity*=(Math.random()*3+1);
		
		String [] input=reader.readLine().split(":");
		//Starting co-ords for clusters.
		int x,y,num,size;
		double angle,r;
		//Sector Gen
		data+="[WORLD_SIZE:"+worldSize+"]\r\n";
		for(int i=1;i<=worldSize;i++){
			data+="[SYSTEM:"+input[i%26]+"]\r\n";
			data+="[DEBRIS:20:"+debrisDensity*(int)(Math.random()*3+1)+"]\r\n";
			//Cluster Gen
			for(int a=0;a<debrisDensity;a++){
				x=(int)(Math.random()*1000);
				y=(int)(Math.random()*800);
				data+="[CLUSTER:"+x+":"+y+"]\r\n";
				for(int n=0;n<20;n++){
					r=Math.random()*150;
					angle=Math.random()*Math.PI*2;
					data+=(x+(int)(r*Math.cos(angle)))+" "+(y+(int)(r*Math.sin(angle)))+"\r\n";
				}
			}
			num=(int)(difficulty+Math.random()*2*i);
			data+="[SPAWNERS:"+num+"]\r\n";
			for(int b=0;b<num;b++){
				x=(int)(Math.random()*1000);
				y=(int)(Math.random()*1000);
				data+=x+" "+y+"\r\n";
			}
		}
		System.out.println(data);
		return data;
	}
	
	private int readSize(String data){
		switch(data.charAt(0)){
			case 'S':
				return 1;
			case 'M':
				return 2;
			case 'L':
				return 3;
			default:
				return -1;
		}
	}
	
	private int readNum(String data){
		switch(data.charAt(0)){
			case 'L':
				return 1;
			case 'M':
				return 2;
			case 'H':
				return 3;
			default:
				return -1;
		}
	}
	
	
	////////
	////////
	
	public static ArrayList<Terrain> getTerrain(){
		return terrain;
	}
	
	public static ArrayList<Ship> getEnemy(){
		return enemies;
	}
	
	public static ArrayList<Ammo> getBullets(){
		return bullets;
	}
}
