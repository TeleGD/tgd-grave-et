package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Plateforme extends Rectangle {
	private float posx;
	private float posy;
	private boolean sens; /* sens de la plateforme */
	private float longueur;
	private float epaisseur;
	private float speed;
	
	
	public void render (GameContainer container, StateBasedGame game, Graphics g,boolean gravite) {
		/* Méthode exécutée environ 60 fois par seconde */
		/* gravite = sens de la gravite */
	
		if (gravite==true) {
			g.setColor(Color.green);
			g.fillRect(posx,posy,epaisseur,longueur);
		}
		else {
			g.setColor(Color.red);
			g.fillRect(posx,posy,epaisseur,longueur);
		}
	}
	
	public Plateforme(float posx,float posy,float longueur,float epaisseur,boolean sens) {
		super(posx, posy, sens ? longueur : epaisseur, sens ? epaisseur : longueur );
		this.posx=posx;
		this.posy=posy;
		this.longueur=longueur;
		this.epaisseur=epaisseur;
		this.sens=sens;
	}
	public abstract void update(GameContainer container, StateBasedGame game, int delta);

	
	 
}
