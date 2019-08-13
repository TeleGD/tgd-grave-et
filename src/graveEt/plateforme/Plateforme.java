package graveEt.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import graveEt.Player;

@SuppressWarnings("serial")
public abstract class Plateforme extends Rectangle {
	private static Image piqueH;
	private static Image piqueV;
	private boolean sens; /* sens de la plateforme */
	private boolean destroyed;
	private Image image;
	private Player p;

	static {
		try {
			piqueH = new Image("images/spikesH.png");
			piqueV = new Image("images/spikesV.png");
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context, float dy) {
		/* Méthode exécutée environ 60 fois par seconde */
		/* gravite = sens de la gravite */
		if (sens) {
			if (p.getGravity()==0) {
				context.drawImage(image, x, container.getHeight() / 2 + y - dy);
			} else {
				context.drawImage(piqueH, x, container.getHeight() / 2 + y - dy);
			}
		} else {
			if (this instanceof Portalforme) {
				context.drawImage(image, x, container.getHeight() / 2 + y - dy);
			} else {
				if (p.getGravity()==0) {
					context.drawImage(piqueV, x, container.getHeight() / 2 + y - dy);
				} else {
					context.drawImage(image, x, container.getHeight() / 2 + y - dy);
				}
			}
		}
	}

	public Plateforme(float posx,float posy,float longueur,float epaisseur,boolean sens, Player p, Image image) {
		super(posx, posy, sens ? longueur : epaisseur, sens ? epaisseur : longueur );
		this.sens=sens;
		this.p=p;
		this.image=image;
	}

	public abstract void update(GameContainer container, StateBasedGame game, int delta);

	public float getPosY() {
		return this.y;
	}

	public boolean getSens() {
		return sens;
	}

	public abstract float getSpeedX();

	public abstract float getSpeedY();

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
