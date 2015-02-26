package game;

public class Camera {
	int x,y;
	//All items outside of Camera range will not be drawn, however they will continue to be updated.
	public Camera(){
		x=0;
		y=0;
	}
}
