package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Bonus extends Entity {

	private Circle shape;
	private float radius;
	private Color color;

	public Bonus (float posX, float posY, float radius, Color color) {
		super (posX, posY);
		this.shape = new Circle (posX, posY, radius);
		this.radius = radius;
		this.color = color;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		this.shape.setX (super.getPosX ());
		this.shape.setY (super.getPosY ());
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor (this.color);
		context.fillOval (super.getPosX (), super.getPosY () - ((World) game.getCurrentState ()).getPlayers ().get (0).getPosY (), radius, radius);
	}

	public Shape getShape () {
		return this.shape;
	}

	public float getRadius () {
		return this.radius;
	}

	public abstract void apply (Player player);

	public abstract boolean isApplied ();

}
