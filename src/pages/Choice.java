package pages;

import java.util.Arrays;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import app.AppLoader;
import app.AppMenu;
import app.elements.MenuItem;

public class Choice extends AppMenu {

	private Audio music;

	public Choice(int ID) {
		super(ID);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) {
		super.initSize(container, game, 600, 400);
		super.init(container, game);
		this.setTitle("Choix");
		this.setSubtitle("Etes-vous surs de tenter l'experience ?");
		this.setMenu(Arrays.asList(new MenuItem[] {
			new MenuItem("Grave-ET") {
				public void itemSelected() {
					game.enterState(3, new FadeOutTransition(), new FadeInTransition());
				}
			},
			new MenuItem("Retour") {
				public void itemSelected() {
					game.enterState(0, new FadeOutTransition(), new FadeInTransition());
				}
			}
		}));
		this.setHint("SELECT A GAME");
		this.music = AppLoader.loadAudio("/musics/graveEt/HalloweenTheme.ogg");
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) {
		super.enter(container, game);
		if (!this.music.isPlaying()) {
			this.music.playAsMusic(1, .4f, true);
		}
	}

}
