package verticalPlateformer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Interface {
	private ArrayList<Player> player;
	public Interface(ArrayList<Player> p) {
		this.player = p;
	}
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// Affichage du score
		g.setColor(Color.red);
		for ( Player p : this.player) {
			g.drawString(""+p.getScore(), 399, 10);
		}
	}
	
}
