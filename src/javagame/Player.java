package javagame;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {

	private Handler handler;
	Texture tex = Game.getInstance();
	
	public static boolean attacking = false;
	
	private float gravity = 0.25f;
	private final float maxSpeed = 20;
	
	public static boolean onGround = false;
	
	private SpriteAnimation playerWalk;
	private SpriteAnimation playerWalkLeft;
	
	private SpriteAnimation playerAttackRight;
	private SpriteAnimation playerAttackLeft;
	private SpriteAnimation playerAttackRightStill;
	private SpriteAnimation playerAttackLeftStill;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 0;
		velY = 0;
		
		isAttacking = false;
		faceRight = true;
		playerWalk = new SpriteAnimation(1,tex.player[1],tex.player[2],tex.player[1],tex.player[3],tex.player[1]);
		playerWalkLeft = new SpriteAnimation(1,tex.player[4],tex.player[5],tex.player[6],tex.player[5],tex.player[4]);
		playerAttackRight = new SpriteAnimation(1,tex.player[7],tex.player[8],tex.player[9]);
		playerAttackLeft = new SpriteAnimation(1,tex.player[10],tex.player[11],tex.player[12]);
		playerAttackRightStill = new SpriteAnimation(1,tex.player[13],tex.player[14],tex.player[15]);
		playerAttackLeftStill = new SpriteAnimation(1,tex.player[16],tex.player[17],tex.player[18]);

	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 32);
	}
	public Rectangle getBoundsDown() {
		return new Rectangle(x+4, y+16, 8, 16);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle(x+10, y+2, 6, 28);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle(x, y+2, 6, 28);
	}
	public Rectangle getBoundsUp() {
		return new Rectangle(x+4, y, 8, 16);
	}
	public Rectangle getGround() {
		return new Rectangle(x+4, y+40, 8, 5);
	}

	
	public void tick() {
		
		playerWalk.runAnimation();
		playerWalkLeft.runAnimation();
		
		if(attacking) {
			if(faceRight) {
				if(velX != 0) {
					playerAttackRight.runAnimation();
				} else {
					playerAttackRightStill.runAnimation();
				}
			}
			
			if(faceLeft) {
				if(velX == 0) {
					playerAttackLeftStill.runAnimation();
				}else {
					playerAttackLeft.runAnimation();
				}
			}
		}
		
		if (falling || jumping) {
			velY += gravity;
			
			if (velY > maxSpeed) {
				velY = maxSpeed;
			}
			
		}
		
		
		//move player
		x += velX;
		y += velY;
		
		//x = Game.clamp(x,  360,  1064);
		//y = Game.clamp(y,  0,  Game.HEIGHT - 40);
		collision();
	}
	
	//collision
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.LavaClump) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (id == ID.Player) {
						HUD.HEALTH -= 2;
					}
					if (id == ID.Player2) {
						HUD.HEALTH2 -= 2;
					}
				}		
			}
			
			if (tempObject.getId() == ID.Boss) {
				if(tempObject.dead)
					continue;
				if(attacking) {
					if(getAttackLeftBounds().intersects(tempObject.getRightBounds())) {
						tempObject.Health--;
					}
					if(getAttackRightBounds().intersects(tempObject.getLeftBounds())) {
						tempObject.Health--;
					}
				}
				if(getBounds().intersects(tempObject.getRightBounds())) {
					x+=4;
				}
				if(getBounds().intersects(tempObject.getLeftBounds())) {
					x-=4;
				}
			}
			if (tempObject.getId() == ID.BasicEnemy) {
				if(tempObject.dead)
					continue;
				
				if(attacking) {
					if(faceLeft) {
						if(getAttackLeftBounds().intersects(tempObject.getAttackRightBounds()) && tempObject.isAttacking && tempObject.faceRight) {
							tempObject.x -= 5;
							x += 5;
						}else if (getAttackLeftBounds().intersects(tempObject.getBounds())) {
							tempObject.Health--;
							tempObject.x -= 5;
						}else if (tempObject.getAttackLeftBounds().intersects(getBounds()) && tempObject.faceLeft && tempObject.isAttacking) {
							HUD.HEALTH--;
							x -= 5;
						}
					}
					if(faceRight) {
						if(getAttackRightBounds().intersects(tempObject.getAttackLeftBounds()) && tempObject.isAttacking && tempObject.faceLeft) {
							tempObject.x += 5;
							x -= 5;
						}else if (getAttackRightBounds().intersects(tempObject.getBounds())) {
							tempObject.Health--;
							tempObject.x -= 5;
						}else if (tempObject.getAttackRightBounds().intersects(getBounds()) && tempObject.faceRight && tempObject.isAttacking) {
							HUD.HEALTH--;
							x += 5;
						}
					}
				}else {
					if (tempObject.getAttackRightBounds().intersects(getBounds()) && tempObject.faceRight && tempObject.isAttacking) {
						HUD.HEALTH--;
						x += 5;
					}
					if (tempObject.getAttackLeftBounds().intersects(getBounds()) && tempObject.faceLeft && tempObject.isAttacking) {
						HUD.HEALTH--;
						x -= 5;
					}
				
				}
			}
			
			if (tempObject.getId() == ID.Block || tempObject.getId() == ID.LavaClump) {
				
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					
					y = tempObject.getY()-32;
					velY = 0;
					falling = false;
					jumping = false;
					//x = lastX;
					//y = lastY;
					
				} else {
					falling = true;
				}
				
				if (getBoundsUp().intersects(tempObject.getBounds())) {
					
					y = tempObject.getY()+16;
					velY = 0;
					falling = true;
					jumping = true;
					//x = lastX;
					//y = lastY;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
	
					x = tempObject.getX()+16;
					velX = 0;
					
					//x = lastX;
					//y = lastY;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
	
					x = tempObject.getX()-16;
					velX = 0;
					
					//x = lastX;
					//y = lastY;
				}
			}
		}
	}
	
	public void render(Graphics g) {

		
		//still player
		if(velX == 0) {
			if(attacking) {
				if(faceLeft) {
					playerAttackLeftStill.drawAnimation(g, x-9, y,22,32);
				}else {
					playerAttackRightStill.drawAnimation(g, x, y,22,32);
				}
			} else {
				g.drawImage(tex.player[0], x, y, null);
			}
			
		} else if(velX > 0) {
			//walking right player
			if(attacking) {
				playerAttackRight.drawAnimation(g, x, y,22,32);
			}else {
				playerWalk.drawAnimation(g, x, y, 16, 32);
			}
		} else {
			//walking left player
			if(attacking) {
				playerAttackLeft.drawAnimation(g, x-9, y,22,32);
			}else {
				playerWalkLeft.drawAnimation(g, x, y, 16, 32);
			}
		}
		

	}

}
