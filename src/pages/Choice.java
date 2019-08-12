package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;
import verticalPlateformer.pages.RulesPage;

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
			new MenuItem ("HaxBall") {
				public void itemSelected () {
					((haxBall.World) game.getState (4)).setState (0);
					game.enterState (4, new FadeOutTransition (), new FadeInTransition ());
				}
			},
			new MenuItem ("Grave-ET") {
				public void itemSelected () {
					((verticalPlateformer.World) game.getState (5)).setState (0);
					if (firstStart) {
						firstStart=false;
						((RulesPage) game.getState(8)).setNextPageID(((verticalPlateformer.World) game.getState (5)).getID());
						((RulesPage) game.getState(8)).setPrevPageID(Choice.this.getID());
						game.enterState (8, new FadeOutTransition (), new FadeInTransition ());
					} else {
						game.enterState (5, new FadeOutTransition (), new FadeInTransition ());
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
			Welcome.music.loop(1, (float) 0.4);
		}
	}

}
