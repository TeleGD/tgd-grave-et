package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;

public class Pause extends AppMenu {
	
	private int worldID;
	
	public Pause (int ID, int worldID) {
		super (ID);
		this.worldID = worldID;
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("Pause");
		this.setSubtitle ("Le temps de prendre un goûter");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem ("Retour") {
				public void itemSelected () {
					if (game.getState (Pause.this.worldID) instanceof verticalPlateformer.World ) {
						((verticalPlateformer.World) game.getState (Pause.this.worldID)).setState (2);
					} else {
						((haxBall.World) game.getState (Pause.this.worldID)).setState (2);
					}
					game.enterState (Pause.this.worldID, new FadeOutTransition (), new FadeInTransition ());
				}
			},
			new MenuItem ("Abandon") {
				public void itemSelected () {
					if (game.getState (Pause.this.worldID) instanceof verticalPlateformer.World ) {
						((verticalPlateformer.World) game.getState (Pause.this.worldID)).setState (0);
					} else {
						((haxBall.World) game.getState (Pause.this.worldID)).setState (0);
					}
					game.enterState (1, new FadeOutTransition (), new FadeInTransition ());
				}
			}
		}));
		this.setHint ("HAVE A SNACK");
	}

}
