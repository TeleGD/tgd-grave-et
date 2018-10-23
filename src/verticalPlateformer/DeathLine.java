package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class DeathLine {

	private Color color;
	private float posY;
	private float speed;

	public DeathLine (GameContainer container) {
		this.color = new Color (240, 0, 0, 204);
		this.posY = container.getHeight () * 4;
		this.speed = -.24f;
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
		g.setColor (this.color);
		g.drawLine (0f, container.getHeight() / 2 + this.posY - dy, (float) container.getWidth(), container.getHeight() / 2 + this.posY - dy);
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		this.posY += this.speed * delta;
	}

}
