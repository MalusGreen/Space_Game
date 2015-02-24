package world;

import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;

public class World {
	Map[] sectors;
	public World(){
		
	}
	public void draw(Graphics g){
		sectors[0].draw(g);
	}
	public void createWorld() throws IOException{
//		BufferedWriter writer = null;
//		BufferedReader reader = null;
		String fileName="World_"+saveNum()+".txt";
		try(BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));BufferedReader reader=new BufferedReader(new FileReader("WorldGen.txt"))){
			writer.write(newWorld(reader));
		}
	}
	
	public void readWorld(String path) throws FileNotFoundException, IOException{
		try(BufferedReader reader=new BufferedReader(new FileReader(path))){
			String input;
			String input_list[];
			int size, num;
			
			input=reader.readLine();
			sectors=new Map[Integer.parseInt(input.substring(input.indexOf(":"), input.indexOf("]")))];
			for(int i=0;i<sectors.length;i++){
				
				//Creating System.
				input=reader.readLine();
				sectors[i]=new Map(input.substring(input.indexOf(":"), input.indexOf("]")));
				
				//Creating Debris.
				input=reader.readLine();
				input_list=input.substring(input.indexOf(":"), input.indexOf("]")).split(":");
				num=Integer.parseInt(input_list[0]);
				size=Integer.parseInt(input_list[1]);
				
				
				while(){
					for(int t=0;t<){
						
					}
				}
			}
		}
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
		difficulty*=5;
		
		String [] input=reader.readLine().split(":");
		//Starting co-ords for clusters.
		int x,y,num,size;
		double angle,r;
		//Sector Gen
		data+="[WORLD_SIZE:"+worldSize+"]\n";
		for(int i=1;i<=worldSize;i++){
			data+="[SYSTEM:"+input[i]+"]\n";
			data+="[DEBRIS:20:"+debrisDensity*(int)(Math.random()*3+1)+"]\n";
			//Cluster Gen
			for(int a=0;a<debrisDensity;a++){
				x=(int)(Math.random()*1000);
				y=(int)(Math.random()*1000);
				data+="[CLUSTER:"+x+":"+y+"]\n";
				for(int n=0;n<20;n++){
					r=Math.random()*500;
					angle=Math.random()*Math.PI*2;
					data+=(x+(int)(r*Math.cos(angle)))+" "+(y+(int)(r*Math.sin(angle)))+"\n";
				}
			}
			num=(int)(difficulty+Math.random()*2*i);
			data+="[SPAWNERS:"+num+"]\n";
			for(int b=0;b<num;b++){
				x=(int)(Math.random()*1000);
				y=(int)(Math.random()*1000);
				data+=x+" "+y+"\n";
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
}
