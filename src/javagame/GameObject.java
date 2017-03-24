package javagame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	
	public boolean dead = false;
	protected int x, y;
	protected ID id;
	protected float velX, velY;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean canJump = false;
	public int Health;
	public boolean isAttacking;

	public boolean faceLeft = false;
	public boolean faceRight = false;
	
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public boolean isFalling() {
		return falling;
	}

	public Rectangle getAttackLeftBounds() {
		return new Rectangle(x-3, y+6, 3, 10);
	}
	
	public Rectangle getAttackRightBounds() {
		return new Rectangle(x+16, y+6, 3, 10);
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}

	public Rectangle getRightBounds() {
		return new Rectangle(x+40, y-256,65,512);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle(x-20, y-256,65,512);
	}

}
