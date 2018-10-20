package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class DeathLine {
	
	private int posX;
	private int speed;
	private int accel;
	
	public DeathLine() {
		this.posX = 50;
		this.speed = 5;
		this.accel = 1;
	}
	
	// Getters et setters des attributs de la death line
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getAccel() {
		return this.accel;
	}
	
	public void setPosX(int x) {
		this.posX = x;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public void setAccel(int a) {
		this.accel = a;
	}
	
	// Méthodes de déplacement (fluide) de la death line
	public void move() {
		
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// Initialisation d'une ligne rouge après le bas de l'écran
		g.setColor(new Color(240, 0, 0, 204));
		g.drawLine((float) this.posX + container.getHeight(), (float) 0, (float) this.posX + container.getHeight(), container.getWidth());
	}
	
	public void update(GameContainer container, StateBasedGame game) {
		this.posX -= this.speed;
	}
	
}
