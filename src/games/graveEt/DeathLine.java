package games.graveEt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class DeathLine {

	private float posY;
	private float speed;
	private static Image souls;

	static {
		souls = AppLoader.loadPicture("/images/graveEt/souls.png");
	}

	public DeathLine (GameContainer container) {
		this.posY = container.getHeight () * 3;
		this.speed = -.32f;
	}

	public float getPosY () {
		return this.posY;
	}

	public float getSpeed () {
		return this.speed;
	}

	public void setPosY (float posY) {
		this.posY = posY;
	}

	public void setSpeed (float speed) {
		this.speed = speed;
	}

	public void render (GameContainer container, StateBasedGame game, Graphics g, float dy) {
		g.drawImage (souls, 0f, container.getHeight()/2+this.posY-dy, container.getWidth(), container.getHeight()/2+this.posY-dy+souls.getHeight(),0,0,souls.getWidth(),souls.getHeight());
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		this.posY += this.speed * delta;
	}

}
