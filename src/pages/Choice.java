package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;
import graveEt.World;
import pages.Rules;

public class Choice extends AppMenu {

	private boolean firstStart;

	public Choice (int ID) {
		super (ID);
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("Choix");
		this.setSubtitle ("CN du 20 octobre 2018");
		this.firstStart=true;
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem ("Grave-ET") {
				public void itemSelected () {
					((World) game.getState (3)).setState (0);
					if (firstStart) {
						firstStart=false;
						((Rules) game.getState(5)).setNextPageID(((World) game.getState (3)).getID());
						((Rules) game.getState(5)).setPrevPageID(Choice.this.getID());
						game.enterState (5, new FadeOutTransition (), new FadeInTransition ());
					} else {
						game.enterState (3, new FadeOutTransition (), new FadeInTransition ());
					}
				}
			},
			new MenuItem ("Retour") {
				public void itemSelected () {
					game.enterState (0, new FadeOutTransition (), new FadeInTransition ());
				}
			}
		}));
		this.setHint ("SELECT A GAME");
	}

	@Override
	public void enter (GameContainer container, StateBasedGame game) {
		super.enter(container, game);
		if (!Welcome.music.playing()) {
			Welcome.music.loop(1, .4f);
		}
	}

}
