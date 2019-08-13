package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;
import graveEt.World;

public class Death extends AppMenu {

	public Death(int ID) {
		super(ID);
	}

	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("C'est tres la mort");
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem ("Rejouer") {
				public void itemSelected () {
					((World) game.getState (3)).setState (0);
					game.enterState (3, new FadeOutTransition (), new FadeInTransition ());
				}
			},
			new MenuItem ("Quitter") {
				public void itemSelected () {
					((World) game.getState (3)).setState (0);
					game.enterState (1, new FadeOutTransition (), new FadeInTransition ());
				}
			}
		}));
	}

	public void setScore(int score) {
		this.setSubtitle ("Seulement "+score+" points...");
	}

}
