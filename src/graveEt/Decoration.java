package graveEt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

public class Decoration {

	private Image skin;
	private float x;
	private float y;

	public Decoration(float x, float y, Image image) {
		this.x=x;
		this.skin=image;
		this.y=y;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g, float dy) {
		g.drawImage(skin, x, container.getHeight() / 2 + y - dy);
	}


}
