package verticalPlateformer.plateforme;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.Player;

@SuppressWarnings("serial")
public class Portalforme extends Plateforme {
	
	private Portalforme couple;
	
	public Portalforme(float posx,float posy, Player p, Image image) {
		super(posx,posy,200,140,false,p,image);
		this.setCouple(this);
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
	}
	
	public float getSpeedX() {
		return 0;
	}
	
	public float getSpeedY() {
		return 0;
	}

	public Portalforme getCouple() {
		return couple;
	}

	public void setCouple(Portalforme couple) {
		this.couple = couple;
	}
}
