package verticalPlateformer.pages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppPage;

public class RulesPage extends AppPage {

	private int nextPageID;
	private int prevPageID;
	private static Image image;

	static {
		try {
			image = new Image("images/verticalPlateformer/rules.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public RulesPage(int ID) {
		super(ID);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput ();
		if (input.isKeyDown (Input.KEY_ESCAPE)) {
			game.enterState (prevPageID, new FadeOutTransition (), new FadeInTransition ());
		}
		if (input.isKeyDown (Input.KEY_ENTER)) {
			game.enterState (nextPageID, new FadeOutTransition (), new FadeInTransition ());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.drawImage(image, 0, 0, container.getWidth(), container.getHeight(), 0, 0, image.getWidth(), image.getHeight());
	}

	public void setNextPageID(int nextPageID) {
		this.nextPageID = nextPageID;
	}

	public void setPrevPageID(int prevPageID) {
		this.prevPageID = prevPageID;
	}

}
