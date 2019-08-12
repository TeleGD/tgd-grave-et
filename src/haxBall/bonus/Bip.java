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

	private int timer;
	private Sound sound;
	private Player p;

	public Bip(int posX, int posY,  Field field) {
		super(posX, posY, new Color(254,222,1), field);
		this.timer = 12*1000;

		try {
			this.sound = new Sound("res/sounds/haxBall/bip.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(!activated) {

		} else if(!deleted) {
			timer -= delta;

			if (timer%500<=16 && timer!=0 ){
				p.resetColor();
				sound.play(1, (float) 0.4);

			} else if (timer>0){
				p.setColor(new Color(0,0,0,0));
			}
		}
		if (timer <= 0) {
			p.resetColor();
			deleted = true;
		}
		super.update(container, game, delta);

	}


	public void activate(Player p, Ball b) {
		activated = true;
		this.p=p;
		sound.play(1, (float) 0.4);
	}
}
