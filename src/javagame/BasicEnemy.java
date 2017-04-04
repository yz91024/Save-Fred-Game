package javagame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	Texture tex = Game.getInstance();
	
	int playerX;
	int playerY;
	
	int deathCount = 50;
	int count = 10;
	int attackCount = 10;
	
	boolean dead = false;
	boolean attacking = true;
	
	private float gravity = 0.15f;
	private final float maxSpeed = 15;
	
	private SpriteAnimation playerWalk;
	private SpriteAnimation playerWalkLeft;
	
	private SpriteAnimation playerAttackRight;
	private SpriteAnimation playerAttackLeft;
	private SpriteAnimation playerAttackRightStill;
	private SpriteAnimation playerAttackLeftStill;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 0;
		
		Health = 20;
		isAttacking = false;
		
		playerWalk = new SpriteAnimation(3,tex.enemy[1],tex.enemy[2],tex.enemy[1],tex.enemy[3],tex.enemy[1]);
		playerWalkLeft = new SpriteAnimation(3,tex.enemy[4],tex.enemy[5],tex.enemy[6],tex.enemy[5],tex.enemy[4]);
		playerAttackRight = new SpriteAnimation(3,tex.enemy[7],tex.enemy[8],tex.enemy[9]);
		playerAttackLeft = new SpriteAnimation(3,tex.enemy[10],tex.enemy[11],tex.enemy[12]);
		playerAttackRightStill = new SpriteAnimation(3,tex.enemy[13],tex.enemy[14],tex.enemy[15]);
		playerAttackLeftStill = new SpriteAnimation(3,tex.enemy[16],tex.enemy[17],tex.enemy[18]);

	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 32);
	}
	
	
	public Rectangle getBoundsDown() {
		return new Rectangle(x+4, y+16, 8, 16);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle(x+12, y+2, 4, 28);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y+2, 4, 28);
	}
	public Rectangle getBoundsUp() {
		return new Rectangle(x+4, y, 8, 16);
	}
	
	public void tick() {
		
		if(dead)
			return;
		
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		playerAttackRight.runAnimation();
		playerAttackLeft.runAnimation();
		
		playerAttackRightStill.runAnimation();
		playerAttackLeftStill.runAnimation();
		
		move();
		
		isAttacking = attacking;
		collision();
		
		if (falling || jumping) {
			velY += gravity;
			
			if (velY > maxSpeed) {
				velY = maxSpeed;
			}
			
		}
		
		if(Health <= 0) {
			dead = true;
		}
		
		
		//move player
		x += velX;
		y += velY;
		
		count--;
		
		
	}
	
	//collision
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				playerX = tempObject.getX();
				playerY = tempObject.getY();
			}
			
			if (tempObject.getId() == ID.LavaClump) {
				if (getBounds().intersects(tempObject.getBounds())) {
					Health--;
				}		
			}
			
			
			if (tempObject.getId() == ID.Block || tempObject.getId() == ID.LavaClump) {
				
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					
					y = tempObject.getY()-32;
					velY = 0;
					falling = false;
					jumping = false;
					
				} else {
					falling = true;
				}
				if (getBoundsUp().intersects(tempObject.getBounds())) {
					
					y = tempObject.getY()+16;
					velY = 0;
					falling = true;
					jumping = true;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
	
					x = tempObject.getX()+16;
					velX = 0;
					
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
	
					x = tempObject.getX()-16;
					velX = 0;
					
				}
			}
		}
	}
	
	//follow the player
	public void move() {
		if((x-playerX) < 300 && (x-playerX) > -300) {
			if(x > playerX) {
				//delay turn speed
				if(count <=0) {
					velX = -3;
					count = 10;
				}
			}
			if(x < playerX) {
				if(count <=0) {
					velX = 3;
					count = 10;
				}
			}
			attacking = true;
		}else {
			attacking = false;
		}
	}
	
	public void render(Graphics g) {
		
		if(dead)
			return;
		
		g.setColor(Color.gray);
		g.fillRect(x-4, y-8, 20, 3);
		g.setColor(Color.RED);
		g.fillRect(x-4, y-8, Health, 3);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(x-4, y-8, 20, 3);
		
		//still player
		if(velX == 0) {
			if(attacking) {
				if(faceLeft) {
					playerAttackLeftStill.drawAnimation(g, x-9, y,22,32);
				}else {
					playerAttackRightStill.drawAnimation(g, x, y,22,32);
				}
			} else {
				g.drawImage(tex.enemy[0], x, y, null);
			}
			
		} else if(velX > 0) {
			faceRight = true;
			faceLeft = false;
			//walking right player
			if(attacking) {
				playerAttackRight.drawAnimation(g, x, y,22,32);
			}else {
				playerWalk.drawAnimation(g, x, y, 16, 32);
			}
		} else {
			faceRight = false;
			faceLeft = true;
			//walking left player
			if(attacking) {
				playerAttackLeft.drawAnimation(g, x-9, y,22,32);
			}else {
				playerWalkLeft.drawAnimation(g, x, y, 16, 32);
			}
		}
		

	}

}
