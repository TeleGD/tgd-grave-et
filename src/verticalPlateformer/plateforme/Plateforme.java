package verticalPlateformer.plateforme;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

@SuppressWarnings("serial")
public abstract class Plateforme extends Rectangle {
	private boolean sens; /* sens de la plateforme */


	public void render (GameContainer container, StateBasedGame game, Graphics g, float dy) {
		/* Méthode exécutée environ 60 fois par seconde */
		/* gravite = sens de la gravite */
			g.setColor(Color.green);
			g.fillRect(x, container.getHeight() / 2 + y - dy,width,height);
			//g.setColor(Color.red);
			//g.draw(this);
	}

	public Plateforme(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx, posy, sens ? longueur : epaisseur, sens ? epaisseur : longueur );
		this.setX(posx);
		this.setY(posy);
		this.setWidth(sens ? longueur : epaisseur);
		this.setHeight(sens ? epaisseur : longueur);
		this.sens=sens;
	}

	public abstract void update(GameContainer container, StateBasedGame game, int delta);

	public float getPosY() {
		return this.y;
	}

	public boolean getSens() {
		return sens;
	}

	public abstract void collideWithPlayer(Player player);

	public abstract float getSpeedX();

	public abstract float getSpeedY();


}
