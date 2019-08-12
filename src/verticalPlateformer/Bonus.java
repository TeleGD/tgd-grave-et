package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Bonus extends Entity {

	private Circle shape;
	private float radius; //rayon
	private Color color = Color.red;
	private Image image;
	private Player player;
	private int gravity;

	public Bonus (float posX, float posY, float radius, Player player, Color color) {
		super (posX, posY);
		this.shape = new Circle (posX+radius, posY+radius, radius);
		this.radius = radius;
		this.color = color;
		this.player = player;
		this.setGravity(-player.getGravity());
		this.setDirY(0.12f);
	}

	public Bonus(float posX, float posY, float radius, Player player, Image image) {
		super (posX, posY);
		this.shape = new Circle (posX+radius, posY+radius, radius);
		this.radius = radius;
		this.image = image;
		this.player = player;
		this.setGravity(-player.getGravity());
		this.setDirY(0.12f);
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		super.update (container, game, delta);
		if (super.getPosX()>container.getWidth()) {
			this.teleport(-2*radius, super.getPosY());
		} else if (super.getPosX()<-2*radius) {
			this.teleport(container.getWidth(), super.getPosY());
		}

		this.shape.setX (super.getPosX ());
		this.shape.setY (super.getPosY ());
		if (player.getGravity()!=-gravity) {
			this.setGravity(-player.getGravity());
		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		if (image==null) {
			context.setColor (this.color);
			context.fillOval (super.getPosX (), container.getHeight() / 2 + super.getPosY() - ((World) game.getCurrentState()).getPlayers().get(0).getPosY(), 2*radius, 2*radius);
		} else {
			context.drawImage(image, super.getPosX (), container.getHeight() / 2 + super.getPosY() - ((World) game.getCurrentState()).getPlayers().get(0).getPosY(), super.getPosX ()+2*radius, container.getHeight()/2+super.getPosY()-((World) game.getCurrentState()).getPlayers().get(0).getPosY()+2*radius, 0, 0, image.getWidth(), image.getHeight());
		}
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
