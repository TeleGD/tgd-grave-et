package pages;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import app.AppPage;
import transitions.VerticalTransition;

public class Welcome extends AppPage {

	private Image logo;
	private Image background;
	private Image transition;
	private Music ambiance;

	private boolean logoVisibility;

	protected int logoBoxWidth;
	protected int logoBoxHeight;
	protected int logoBoxX;
	protected int logoBoxY;

	private int logoWidth;
	private int logoHeight;
	private int logoX;
	private int logoY;

	private int logoNaturalWidth;
	private int logoNaturalHeight;

	public Welcome (int ID) {
		super (ID);
	}

	@Override
	public void init (GameContainer container, StateBasedGame game) {
		super.initSize (container, game, container.getWidth()/3, container.getHeight()*7/8);
		super.init (container, game);

		this.logoBoxX = this.contentX;
		this.logoBoxY = this.hintBoxY + this.hintBoxHeight + AppPage.gap;
		this.logoBoxWidth = this.contentWidth;
		this.logoBoxHeight = this.contentY + this.contentHeight - this.logoBoxY;

		this.logoVisibility = false;

		this.titleVisibility = false;
		this.subtitleVisibility = false;
		this.hintBlink = true;

		this.setHint ("PRESS [START]");
		try {
			this.background = new Image("images/welcome.png");
			this.transition = new Image("images/soulsTransition.png");
			this.setLogo (new Image ("images/logo.png"));
			this.ambiance = new Music("res/musics/HalloweenTheme.ogg");
			ambiance.play();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update (GameContainer container, StateBasedGame game, int  delta) {
		super.update (container, game, delta);
		Input input = container.getInput ();
		if (input.isKeyDown (Input.KEY_ESCAPE)) {
			container.exit ();
		} else if (input.isKeyDown (Input.KEY_ENTER)) {
			game.enterState (1, new VerticalTransition (Color.black, transition), new EmptyTransition ());
		}
	}

	@Override
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.drawImage(background, 0, 0, container.getWidth(), container.getHeight(), 0, 0, background.getWidth(), background.getHeight());
		super.render (container, game, context);
		this.renderLogo (container, game, context);
	}

	private void renderLogo (GameContainer container, StateBasedGame game, Graphics context) {
		if (this.logoVisibility) {
			context.drawImage (
				this.logo,
				this.logoX,
				this.logoY,
				this.logoX + this.logoWidth,
				this.logoY + this.logoHeight,
				0,
				0,
				this.logoNaturalWidth,
				this.logoNaturalHeight
			);
		}
	}

	public void setLogo (Image logo) {
		this.logo = logo.copy ();
		this.logoNaturalWidth = logo.getWidth ();
		this.logoNaturalHeight = logo.getHeight ();
		this.logoWidth = Math.min (this.logoBoxWidth, this.logoNaturalWidth);
		this.logoHeight = Math.min (this.logoBoxHeight, this.logoNaturalHeight);
		if (this.logoWidth * this.logoNaturalHeight < this.logoNaturalWidth * this.logoHeight) {
			this.logoHeight = this.logoNaturalHeight * this.logoWidth / this.logoNaturalWidth;
		} else {
			this.logoWidth = this.logoNaturalWidth * this.logoHeight / this.logoNaturalHeight;
		}
		this.logoX = this.logoBoxX + (this.logoBoxWidth - this.logoWidth) / 2;
		this.logoY = this.logoBoxY + (this.logoBoxHeight - this.logoHeight) / 2;
	}

	public Image getLogo () {
		return logo.copy ();
	}

}
