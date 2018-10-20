package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Bonus {
	private int posX, posY;
	private Color color;
	
	public Bonus(int posX, int posY, Color color) {
		this.posX = posX;
		this.posY = posY;
		this.color = color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public abstract void update (GameContainer container, StateBasedGame game, int delta);
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.fillOval(posX, posY, 10, 10);
	}
}