package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Field;

public class Flash extends Bonus {

	private boolean activated, deleted;
	private Color fieldColor;
	private Field field;
	private int timer;
	
	public Flash(int posX, int posY, double fieldWidth, Field field) {
		super(posX, posY, new Color(255,255,255), fieldWidth);
		this.field = field;
		this.fieldColor = field.getColor();
		this.timer = 6*1000;
		
		activated = false;
		deleted = false;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(!activated) {
			super.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			
		} else if(!deleted) {
			timer -= delta;
			field.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		}
		
		if (timer <= 0) {
			field.setColor(fieldColor);
			deleted = true;
		}
		
	}

	public boolean isActivated() {
		return activated;
	}

	public void activate() {
		activated = true;
	}

	public boolean isDeleted() {
		return deleted;
	}

}
