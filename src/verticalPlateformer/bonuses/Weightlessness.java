package verticalPlateformer.bonuses;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Bonus;
import verticalPlateformer.Player;

public class Weightlessness extends Bonus {

	private static Color color;

	static {
		Weightlessness.color = new Color (0, 255, 0, 51);
	}

	private boolean applied;
	private int count;
	private Player player;

	public Weightlessness (float posX, float posY, float radius) {
		super (posX, posY, radius, Weightlessness.color);
		super.freeze ();
		this.applied = false;
		this.count = 2000;
		this.player = null;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		if (this.player != null && !this.applied) {
			if (!this.player.isFrozen ()) {
				this.applied = true;
			} else {
				this.count -= delta;
				if (this.count <= 0) {
					this.applied = true;
					this.player.unFreeze ();
				}
			}
		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		if (this.player == null) {
			super.render (container, game, context);
		}
	}

	public void apply (Player player) {
		if (player != null && this.player == null) {
			this.player = player;
			this.player.freeze ();
		}
	}

	public boolean isApplied () {
		return this.applied;
	}

}
