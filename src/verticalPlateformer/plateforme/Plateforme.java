package verticalPlateformer.plateforme;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

public abstract class Plateforme extends Rectangle {
	protected float posx;
	protected float posy;
	private boolean sens; /* sens de la plateforme */
	private float width;
	private float height;


	public void render (GameContainer container, StateBasedGame game, Graphics g, float dy) {
		/* Méthode exécutée environ 60 fois par seconde */
		/* gravite = sens de la gravite */

			g.setColor(Color.green);
			g.fillRect(posx, container.getHeight() / 2 + posy - dy,width,height);

	}

	public Plateforme(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx, posy, sens ? longueur : epaisseur, sens ? epaisseur : longueur );
		this.posx=posx;
		this.posy=posy;
		this.width= sens ? longueur : epaisseur;
		this.height= sens ? epaisseur : longueur;
		this.sens=sens;

	}

	public abstract void update(GameContainer container, StateBasedGame game, int delta);

	public float getPosY() {
		return this.posy;
	}

	public boolean getSens() {
		return sens;
	}

	public abstract void collideWithPlayer(Player player);

	public abstract float getSpeedX();

	public abstract float getSpeedY();


}
