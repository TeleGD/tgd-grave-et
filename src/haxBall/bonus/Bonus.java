package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Bonus {
	private int posX, posY;
	private Color color;
	private int radius;
	
	public Bonus(int posX, int posY, Color color, double fieldWidth) {
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.radius = (int) (10*fieldWidth); //magic numbers j'emmerde tout le monde
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public abstract void update (GameContainer container, StateBasedGame game, int delta);
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		Color oldColor = context.getColor();
		context.setColor(color);
		
		context.fillOval(posX, posY, radius, radius);
		
		context.setColor(oldColor);
	}
	
	public abstract boolean isActivated();
	public abstract void activate();
	public abstract boolean isDeleted();
}