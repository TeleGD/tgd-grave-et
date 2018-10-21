package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public abstract class Bonus {
	private int posX, posY;
	private Color color;
	private int diam;
	protected boolean activated, deleted;
	private int timer;
	protected Field field;
	
	public Bonus(int posX, int posY, Color color, Field field) {
		this.posX = posX;
		this.posY = posY;
		this.color = color;
		this.diam = (int) (0.02*field.getWidth());
		this.field = field;
		
		this.timer = 7*1000;
		
		this.activated = false;
		this.deleted = false;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		if(!activated)
			timer -= delta;
		
		if (timer <= 0) {
			deleted = true;
		}
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		Color oldColor = context.getColor();
		context.setColor(color);
		
		context.fillOval(posX, posY, diam, diam);
		
		context.setColor(oldColor);
	}
	
	public boolean isDeleted() {
		return deleted;
	}
	
	public boolean isActivated() {
		return activated;
	}
	
	public abstract void activate(Player p, Ball b);
}