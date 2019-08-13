package graveEt.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import graveEt.Player;

@SuppressWarnings("serial")
public class PlateformeClassique extends Plateforme {

	public PlateformeClassique(float posx,float posy,float longueur,float epaisseur,boolean sens, Player p, Image image) {
		super(posx,posy,longueur,epaisseur,sens,p,image);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
	}

	public float getSpeedX() {
		return 0;
	}

	public float getSpeedY() {
		return 0;
	}
}
