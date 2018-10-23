package verticalPlateformer;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppMenu;
import app.elements.MenuItem;

public class DeathPage extends AppMenu{
	
	private static int score;
	
	public DeathPage(int ID) {
		super(ID);
		DeathPage.score = 0;
	}
	
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, 600, 400);
		super.init (container, game);
		this.setTitle ("C'est tres la mort");
		this.setSubtitle (score+" points...");
		// TODO Ca marche pas
		this.setMenu (Arrays.asList (new MenuItem [] {
			new MenuItem ("Quitter") {
				public void itemSelected () {
					game.enterState (0, new FadeOutTransition (), new FadeInTransition ());
				}
			}
		}));
	}
	
	public static void setScore(int score) {
		DeathPage.score = score;
	}

}
