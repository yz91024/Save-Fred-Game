package javagame;

import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	public void tick() {
		//loop through and tick every object
		for (int i = 0; i < object.size(); i++) {
			try{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			} catch(Exception e) {
				
			}
		}
	}
 
	public void render(Graphics g) {
		//render all objects
		for (int i = 0; i < object.size(); i++) {
			try{
				GameObject tempObject = object.get(i);
			
				tempObject.render(g);
				
			} catch(Exception e) {
				
			}
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
