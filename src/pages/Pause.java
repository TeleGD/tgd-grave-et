package pages;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;
import verticalPlateformer.pages.RulesPage;

public class Pause extends AppMenu {
	
	private int worldID;
	private List<MenuItem> menu;
	
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
		menu = new ArrayList<MenuItem>();
		menu.add(new MenuItem("Retour") {
			public void itemSelected () {
				if (game.getState (Pause.this.worldID) instanceof verticalPlateformer.World ) {
					((verticalPlateformer.World) game.getState (Pause.this.worldID)).setState (2);
				} else {
					((haxBall.World) game.getState (Pause.this.worldID)).setState (2);
				}
				game.enterState (Pause.this.worldID, new FadeOutTransition (), new FadeInTransition ());
			}
		});
		if (game.getState (Pause.this.worldID) instanceof verticalPlateformer.World ) {
			menu.add(new MenuItem ("Règles") {
				public void itemSelected () {
					((RulesPage) game.getState(8)).setNextPageID(Pause.this.getID());
					((RulesPage) game.getState(8)).setPrevPageID(Pause.this.getID());
					game.enterState (8, new FadeOutTransition (), new FadeInTransition ());
				}
			});
		}
		menu.add(new MenuItem ("Abandon") {
			public void itemSelected () {
				if (game.getState (Pause.this.worldID) instanceof verticalPlateformer.World ) {
					((verticalPlateformer.World) game.getState (Pause.this.worldID)).setState (0);
				} else {
					((haxBall.World) game.getState (Pause.this.worldID)).setState (0);
				}
				game.enterState (1, new FadeOutTransition (), new FadeInTransition ());
			}
		});
		this.setMenu (menu);
		this.setHint ("HAVE A SNACK");
	}

}
