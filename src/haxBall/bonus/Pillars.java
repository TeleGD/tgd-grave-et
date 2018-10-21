package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public class Pillars extends Bonus {

	public Pillars(int posX, int posY, Field field) {
		super(posX, posY, new Color(20,20,20), field);	
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(activated) {
			//on fait spawn des balles fixes trololo
		}
		super.update(container, game, delta);
	}

	public void activate(Player p, Ball b) {
		activated = true;		
	}
}
