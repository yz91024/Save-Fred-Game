package javagame;

public class Camera {
	
	public float x, y;
	
	public Camera(float x,float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(GameObject player) {
		
		x = -player.getX() + Game.WIDTH/4;
		y = -player.getY() + Game.HEIGHT/2 + 100;
		
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
