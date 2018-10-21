package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Sound;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public class Flash extends Bonus {

	private Color fieldColor;
	private int timer;
	private Sound sound;
	
	public Flash(int posX, int posY, Field field) {
		super(posX, posY, new Color(255,255,255), field);
		this.fieldColor = field.getColor();
		this.timer = 7*1000;
 
		try {
			this.sound = new Sound("res/sound/flash.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
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
		
		super.update(container, game, delta);
	}
	
	public void activate(Player p, Ball b) {
		activated = true;
		sound.play(1, (float) 0.4);
	}

}
