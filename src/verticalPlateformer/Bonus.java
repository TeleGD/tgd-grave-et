package verticalPlateformer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Bonus extends Entity {

	private int value; // valeur bonus

	public Bonus (float x, float y, int value) {
		super (x, y);
		this.value = value;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {}

	public abstract void render (GameContainer container, StateBasedGame game, Graphics context);

	public int getValue () {
		return this.value;
	}

}
