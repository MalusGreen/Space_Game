package game;

public class Camera {
	int x,y;
	//All items outside of Camera range will not be drawn, however they will continue to be updated.
	public Camera(){
		x=0;
		y=0;
	}
	
	public void center(int x, int y){
		System.out.println("CAMERA: "+x+" "+y);
		this.x=-x+500;
		this.y=-y+400;
	}
}
