package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.World;

public abstract class Bonus {
	private int posX, posY;
	private Color color;
	private World world;
	private int radius;
	
	public Bonus(int posX, int posY, Color color, World w) {
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.world = w;
		this.radius = 10; //magic numbers j'emmerde tout le monde
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public abstract void update (GameContainer container, StateBasedGame game, int delta);
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		Color oldColor = context.getColor();
		context.setColor(color);
		
		context.fillOval(posX, posY, radius*world.getWidth()/1280, radius*world.getHeight()/720);
		
		context.setColor(oldColor);
	}
}