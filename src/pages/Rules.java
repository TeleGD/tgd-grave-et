package pages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;
import app.AppPage;

public class Rules extends AppPage {

	private Image background;
	private Audio music;

	public Rules(int ID) {
		super(ID);
	}

	public void init(GameContainer container, StateBasedGame game) {
		this.background = AppLoader.loadPicture("/images/graveEt/rules.png");
		this.music = AppLoader.loadAudio("/musics/graveEt/HalloweenTheme.ogg");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		this.music.playAsMusic(1, .4f, true);
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) {
		this.music.stop();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) {
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			game.enterState(1, new FadeOutTransition(), new FadeInTransition());
			return;
		} else if (input.isKeyDown(Input.KEY_ENTER)) {
			game.enterState(5, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(this.background, 0, 0, container.getWidth(), container.getHeight(), 0, 0, this.background.getWidth(), this.background.getHeight());
	}

}
