package javagame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Game extends Applet implements Runnable {

	private static final long serialVersionUID = -8630090107797765293L;

	//screen size
	public static int WIDTH = 1080, HEIGHT = 720;
	
	int boss = 0;
	
	private Thread thread;
	private boolean running = false;
	
	public static boolean respawn1 = false;
	public static boolean respawn2 = false;
	public static boolean respawn3 = false;
	public static boolean respawn4 = false;
	public static boolean respawn5 = false;
	
	Graphics dbg;
	
	public static boolean hasBegun = false;
	
	//levels
	public BufferedImage tut1 = null;
	public BufferedImage tut2 = null;
	public BufferedImage level = null;
	public BufferedImage level2 = null;
	public BufferedImage level3 = null;
	
	public BufferedImage tint = null;
	public BufferedImage stick = null;
	
	public static int action = 0;
	
	private BufferedImage background = null;
	private BufferedImage cursor = null;
	private BufferedImage menuBackground = null;
	public static float camX = 0.0f;
	public static float camY = 0.0f;
	
	private BufferedImage fred = null;
	
	//debug
	public static boolean debug = false;
	
	public static int currentLevel = 1;
	
	public static int maxW1;
	public static int maxH1;
	public static int maxW2;
	public static int maxH2;
	public static int maxW3;
	public static int maxH3;
	
	BufferedImage cursorImg = null;
	// Create a new blank cursor.
	Cursor blankCursor = null;
	Cursor customCursor = null;
	
	//jump power
	public static int jumpPower = -6;
	
	//beginning cut scene
	private Animation beginAnimation;
	
	//end cut scene
	private Animation endAnimation;
	
	
	//check if the player has the key
	public static boolean hasKey = false;
	
	//word font
	public Font fnt0;
	
	//handler to tick and render objects
	Handler handler;
	
	//the menu
	private Menu menu;
	
	//camera follows player
	Camera cam;
	
	//textures
	static Texture tex;
	
	//HUD show health bar
	private HUD hud;

	//music files
	Clip menuClip;
	Clip gameClip;
	
	//two player
	public static boolean twoPlayer = false;
	
	//for double buffering
	Image dbImage;
	
	//how long cutscene goes
	int storyCount = 1000;
	
	int musicCount = 20;
	
	//what state the game is in
	public enum STATE {
		MENU,
		HELP,
		GAME,
		BEGIN,
		STORY,
		TUTORIAL,
		END
	};
	
	//Game state. whether the game is playing, paused, or on cut scene
	public static STATE state = STATE.MENU;
	
	//go to next level
	public static boolean nextLevel = false;
	
	//play the music
	public static boolean playGameMusic = false;
	
	public Game() {
		
		//initialize stuff
		tex = new Texture();
				
		menu = new Menu();
		
		fnt0 = new Font("comic sans ms",Font.BOLD,20);
		
		
		handler = new Handler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		tut1 = loader.loadImage("/tut-1.png");
		tut2 = loader.loadImage("/tut-2.png");
		level = loader.loadImage("/level-edited (1).png");
		level2 = loader.loadImage("/level2final.png");
		level3 = loader.loadImage("/level5bossfinal.png");
		background = loader.loadImage("/clouds.png");
		cursor = loader.loadImage("/cursor.png");
		menuBackground = loader.loadImage("/menu.png");
		fred = loader.loadImage("/save-fred.png");
		tint = loader.loadImage("/bob.png");
		stick = loader.loadImage("/stick.png");
		
		beginAnimation = new Animation(80,fred,tex.story[0],tex.story[1],tex.story[2],tex.story[3],tex.story[4],tex.story[5]);
		endAnimation = new Animation(80,fred,tex.story[6],tex.story[7],tex.story[8]);
		
		maxW1 = level.getWidth();
		maxH1 = level.getHeight();
		
		cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		// Create a new blank cursor.
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "cursor");
		
		loadImageLevel(tut1);
		
		cam = new Camera(0,0);
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput());
		this.addMouseMotionListener(new MouseInput());
		
		hud = new HUD();
		
		
		playMenu();
	}
	
	//menu music
	public void playMenu() {
		AudioInputStream inputStream;
		try {
			inputStream = AudioSystem.getAudioInputStream(new File("My_Song_3.wav"));
			menuClip = AudioSystem.getClip();
	        menuClip.open(inputStream);
	        menuClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playGame() {
		AudioInputStream inputStream;
		try {
			inputStream = AudioSystem.getAudioInputStream(new File("My_Song_8.wav"));
			gameClip = AudioSystem.getClip();
	        gameClip.open(inputStream);
	        gameClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playBoss() {
		AudioInputStream inputStream;
		try {
			inputStream = AudioSystem.getAudioInputStream(new File("My_Song_6.wav"));
			gameClip = AudioSystem.getClip();
	        gameClip.open(inputStream);
	        gameClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void init()
	{
		setSize(WIDTH,HEIGHT);
	}
	
	public synchronized void destroy()
	{
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//run stuff
	public void run() {
		/*
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		*/
		while(running) {
			tick();
			repaint();

			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				
			}
			/*
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				
				tick();
				delta--;
			}
				
			repaint();
			frames++;

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS:" + frames);
				HUD.fps = frames;
				frames = 0;
			}
			*/
		}
		stop();
	}
	
	public void update(Graphics g) {
		dbImage = createImage(WIDTH,HEIGHT);
		dbg = dbImage.getGraphics();
		paint(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (state == STATE.GAME || state == STATE.TUTORIAL) {
			g.setColor(new Color(50,80,123));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			//g.drawImage(background, 0,0,WIDTH,HEIGHT,this);
			g2d.translate(cam.getX(),cam.getY()); //begin cam
			
			//clouds?
			for(int xx = 0; xx < background.getWidth()*50;xx+=background.getWidth()*10){
				for(int yy = 0; yy < background.getHeight()*20;yy+=background.getHeight()*10){
					g.drawImage(background, xx,yy,1080,720,this);
				}
			}
			
			handler.render(g);
			g2d.translate(-cam.getX(),-cam.getY()); // end cam
			hud.render(g);
			if(state == STATE.TUTORIAL) {
				Font fnt1 = new Font("comic sans ms",Font.BOLD,18);
				g.setFont(fnt1);
				g.setColor(Color.black);
				tutorial(g);
			}
		} else if ((state == STATE.MENU)||(state==STATE.HELP)){
			g.setColor(Color.gray);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.drawImage(menuBackground, 0, 0,WIDTH,HEIGHT,this);
			menu.render(g);
		}else if(state == STATE.BEGIN) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}else if(state == STATE.STORY){
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			beginAnimation.drawAnimation(g, 0, 0,WIDTH,HEIGHT);
			g.setColor(Color.lightGray);
			g.setFont(fnt0);
			g.drawString("Press Space To Skip", 10, 20);
		}else if(state == STATE.END){
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			endAnimation.drawAnimation(g, 0, 0,WIDTH,HEIGHT);
			g.setColor(Color.lightGray);
			g.setFont(fnt0);
			g.drawString("Press Space To Skip", 10, 20);
		}

	}

	public void tutorial(Graphics g) {
		if(currentLevel == 1&&action == 0) {
			action = 1;
		}
		if(action == 1) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Hey there! I'm Bob!", 250, 200);
		}else if(action == 2) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("You seem new to this", 260, 180);
			g.drawString("game so I'll show you around!", 210, 210);
		}else if(action == 3) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Use the arrow keys", 260, 180);
			g.drawString("to move around.", 270, 210);
		}else if(action == 4) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Press down to", 260, 180);
			g.drawString("pick things up", 270, 210);
		}else if(action == 5) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("and go into portals.", 260, 200);
		}else if(action == 6) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("To go into portals", 260, 180);
			g.drawString("you need the key.", 270, 210);
		}else if(action == 7) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Why don't you give", 260, 150);
			g.drawString("it a try and", 270, 180);
			g.drawString("finish this level?", 250, 210);
		}else if(action == 8) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Don't touch lava!", 260, 200);
			g.drawImage(tex.block[1], 500, 300,100,100, this);
		}else if(action == 9) {
			state = STATE.GAME;
		}else if(action == 10) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Uh oh. Those guys", 260, 180);
			g.drawString("look dangerous.", 270, 210);
			g.drawImage(tex.enemy[0], 700, 400,25,50, this);
		}else if(action == 11) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Here! Take this stick", 260, 150);
			g.drawString("to protect yourself.", 270, 180);
			g.drawString("Press Space to use it!", 250, 210);
			g.drawImage(stick, 500, 300,100,100, this);
		}else if(action == 12) {
			state = STATE.GAME;
		}else if(action == 13) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("So, you're trying to", 260, 150);
			g.drawString("save your friend", 270, 180);
			g.drawString("from the ninja dudes?", 270, 210);
			g.drawString("I think they went this way.", 250, 240);
		}else if(action == 14) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Good luck!", 260, 180);
		}else if(action == 15) {
			state = STATE.GAME;
		}else if(action == 16) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Uh oh. That guy is", 260, 150);
			g.drawString("huge! I think he has", 270, 180);
			g.drawString("the key to the portal.", 270, 210);
			g.drawString("Try hitting him from behind.", 240, 240);
			g.drawImage(tex.boss[0], 600, 250,256,512, this);
		}else if(action == 17) {
			state = STATE.GAME;
		}else if(action == 18) {
			g.drawImage(tint, 0, 0,WIDTH,HEIGHT, this);
			g.drawString("Look! It's your friend!", 260, 180);
		}else if(action == 19) {
			state = STATE.END;
		}
	}
	
	//stuff that happens each tick
	private void tick() {

		
		if(state != STATE.MENU && state != STATE.HELP) {
			menuClip.stop();
		}
		
		if (state == STATE.GAME) {
			
			
			setCursor(blankCursor);
			if(!hasBegun) {
				state = STATE.STORY;
				hasBegun = true;
				playGame();
			}
			
			if(currentLevel == 5 && boss == 0) {
				gameClip.stop();
				playBoss();
				boss = 1;
			}
			
			respawn();
			if(KeyInput.nextLevel) {
				loadLevel(currentLevel + 1);
				
				KeyInput.nextLevel = false;
			}
			
			if(KeyInput.lastLevel) {
				loadLevel(currentLevel - 1);
				KeyInput.lastLevel = false;
			}
			
			handler.tick();
			for (int i = 0; i < handler.object.size(); i++) {
				if (handler.object.get(i).getId() == ID.Player) {
					cam.tick(handler.object.get(i));
				}
			}
			hud.tick();
			camX = cam.getX();
			camY = cam.getY();
			if(currentLevel == 2 && action == 9) {
				action = 10;
				state = STATE.TUTORIAL;
			}
			if(currentLevel == 3 && action == 12) {
				action = 13;
				state = STATE.TUTORIAL;
			}
			if(currentLevel == 5 && action == 15) {
				action = 16;
				state = STATE.TUTORIAL;
			}
			if(currentLevel == 6 && action == 17) {
				action = 18;
				state = STATE.TUTORIAL;
			}
		} else if(state == STATE.STORY){
			setCursor(blankCursor);
			beginAnimation.runAnimation();
		}else if(state == STATE.END){
			setCursor(blankCursor);
			endAnimation.runAnimation();
		} else {
			setCursor(customCursor);
		}
	}
	
	//load level
	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		System.out.println("width, height: " + w + ", " + h);
				
		for (int xx=0;xx<w;xx++) {
			for (int yy=0;yy<h;yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel>>16) & 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				if(red==0 && green==0 && blue==0) {
					handler.addObject(new Block(xx * 16,yy * 16,ID.Block,handler,0));
				}
				if(red==60 && green==60 && blue==60) {
					handler.addObject(new Block(xx * 16,yy * 16,ID.Block,handler,1));
				}
				if(red==255 && green==0 && blue==0) {
					handler.addObject(new Boss(xx * 16,yy * 16,ID.Boss,handler));
				}
				
				if(red==30 && green==30 && blue==30) {
					handler.addObject(new LavaClump(xx * 16,yy * 16,ID.LavaClump));
				}
				
				if(red==200 && green==0 && blue==0) {
					handler.addObject(new BasicEnemy(xx * 16,yy * 16,ID.BasicEnemy,handler));
				}
				
				if(red==0 && green==255 && blue==0) {
					handler.addObject(new Door(xx * 16,yy * 16,ID.Door,handler));
				}
				if(red==0 && green==0 && blue==255) {
					handler.addObject(new Player(xx * 16,yy * 16,ID.Player,handler));
					if(twoPlayer)
						handler.addObject(new Player(xx * 16,yy * 16,ID.Player2,handler));
				}
				if(red==0 && green==255 && blue==255) {
					handler.addObject(new Key(xx * 16,yy * 16,ID.Key,handler));
				}
				if(red==20 && green==20 && blue==20) {
					handler.addObject(new Border(xx * 16,yy * 16,ID.Block,handler));
				}
			}
		}
	}
	
	//respawn
	public void respawn(){
		if(respawn1){
			handler.object.clear();
		
			hasKey = false;
			HUD.HEALTH = 100;
			HUD.COINS = 0;
			loadImageLevel(tut1);
			respawn1 = false;
		}
		
		if(respawn2){
			handler.object.clear();
		
			hasKey = false;
			HUD.HEALTH = 100;
			HUD.COINS = 0;
			loadImageLevel(tut2);
			respawn2 = false;
		}
		
		if(respawn3){
			handler.object.clear();
		
			hasKey = false;
			HUD.HEALTH = 100;
			HUD.COINS = 0;
			loadImageLevel(level);
			respawn3 = false;
		}
		if(respawn4){
			handler.object.clear();
		
			hasKey = false;
			HUD.HEALTH = 100;
			HUD.COINS = 0;
			loadImageLevel(level2);
			respawn4 = false;
		}
		if(respawn5){
			handler.object.clear();
			
			hasKey = false;
			HUD.HEALTH = 100;
			HUD.COINS = 0;
			loadImageLevel(level3);
			respawn5 = false;
		}
	}
	
	//go to a level
	public void loadLevel(int l) {
		handler.object.clear();
		currentLevel = l;
		hasKey = false;
		if(l == 1){
			loadImageLevel(tut1);
		}else if(l == 2) {
			loadImageLevel(tut2);
		}else if(l == 3) {
			loadImageLevel(level);
		}
		else if(l == 4) {
			loadImageLevel(level2);
		}else if(l == 5) {
			loadImageLevel(level3);
		}
	}
	
	//clamp variables
	public static int clamp(int var, int min, int max) {
		if (var >= max) return var = max;
		else if (var <= min) return var = min;
		else return var;
	}
	
	//instance of texture
	public static Texture getInstance() {
		return tex;
	}
	
	
	
	
	//public static void main(String args[]) {
	//	game = new Game();
	//}
	
	
}