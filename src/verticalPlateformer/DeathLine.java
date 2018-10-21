package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class DeathLine {
	
	private float posY;
	private float speed;
	
	public DeathLine(GameContainer container) {
		this.posY = container.getHeight();
		this.speed = 2;
	}
	
	// Getters et setters des attributs de la death line
	
	public float getPosY() {
		return this.posY;
	}
	
	public float getSpeed() {
		return this.speed;
	}
	
	public void setPosY(float y) {
		this.posY = y;
	}
	
	public void setSpeed(float s) {
		this.speed = s;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// Initialisation d'une ligne rouge apres le bas de l'ecran
		g.setColor(new Color(240, 0, 0, 204));
		g.drawLine((float) 0, (float) this.posY, (float) container.getWidth(), (float) this.posY);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		this.posY -= this.speed;
	}
	
}
