package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.Sound;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public class Bip extends Bonus {


	private Color fieldColor;
	private Field field;
	private int timer;
	private Sound sound;
	private Player p;
	private Color meme;
	
	public Bip(int posX, int posY,  Field field) {
		super(posX, posY, new Color(255,255,255), field);
		this.field = field;
		this.fieldColor = field.getColor();
		this.timer = 10*1000;
		try {
			this.sound = new Sound("res/sound/bip.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(!activated) {
			
		
			
		} else if(!deleted) {
			timer -= delta;
			
			if (timer%1000==0 && timer!=0 ){
				p.setColor(meme);
				sound.play(1, (float) 0.4);
				
			} else {
				p.setColor(field.getColor());
			}
		}
		if (timer <= 0) {
			p.setColor(meme);
			deleted = true;
		}
		super.update(container, game, delta);
		
	}


	public void activate(Player p, Ball b) {
		activated = true;
		meme = p.getColor();
		this.p=p;
		sound.play(1, (float) 0.4);
	}
}


