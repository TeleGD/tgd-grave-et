package verticalPlateformer.plateforme;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

@SuppressWarnings("serial")
public abstract class Plateforme extends Rectangle {
	private static Image plateformeH1;
	private static Image plateformeV1;
	private static Image plateformeH2;
	private static Image plateformeV2;
	private static Image piqueH;
	private static Image piqueV;
	private boolean sens; /* sens de la plateforme */
	private boolean destroyed;
	private Image image;
	private Player p;

	static {
		try {
			plateformeH1 = new Image("images/verticalPlateformer/plateformeH1.png");
			plateformeH2 = new Image("images/verticalPlateformer/plateformeH2.png");
			plateformeV1 = new Image("images/verticalPlateformer/plateformeV1.png");;
			plateformeV2 = new Image("images/verticalPlateformer/plateformeV2.png");
			piqueH = new Image("images/verticalPlateformer/spikesH.png");
			piqueV = new Image("images/verticalPlateformer/spikesV.png");
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}

	public void render (GameContainer container, StateBasedGame game, Graphics g, float dy) {
		/* Méthode exécutée environ 60 fois par seconde */
		/* gravite = sens de la gravite */
		if (sens) {
			if (p.getGravity()==0) {
				g.drawImage(image, x, container.getHeight() / 2 + y - dy);
			} else {
				g.drawImage(piqueH, x, container.getHeight() / 2 + y - dy);
			}
		} else {
			if (p.getGravity()==0) {
				g.drawImage(piqueV, x, container.getHeight() / 2 + y - dy);
			} else {
				g.drawImage(image, x, container.getHeight() / 2 + y - dy);
			}
		}
		//g.setColor(Color.red);
		//g.draw(this);
	}

	public Plateforme(float posx,float posy,float longueur,float epaisseur,boolean sens, Player p) {
		super(posx, posy, sens ? longueur : epaisseur, sens ? epaisseur : longueur );
		this.sens=sens;
		this.p=p;
		if (sens) {
			image = new Random().nextBoolean() ? plateformeH1 : plateformeH2;
		} else {
			image = new Random().nextBoolean() ? plateformeV1 : plateformeV2;
		}
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
