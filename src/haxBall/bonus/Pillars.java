package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Field;

public class Pillars extends Bonus {
	
	private boolean activated, deleted;

	public Pillars(int posX, int posY, double fieldWidth) {
		super(posX, posY, new Color(20,20,20), fieldWidth);
		
		this.activated = false;
		this.deleted = false;		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(activated) {
			//on fait spawn des balles fixes trololo
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
