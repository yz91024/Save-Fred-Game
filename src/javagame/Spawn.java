package javagame;


public class Spawn {
	
	private int count = 0;
	private int x;
	private int y;

	private boolean activated;
	
	public Spawn(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(count >= 100) {
			this.activated = true;
		}
		count++;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	
	
}
