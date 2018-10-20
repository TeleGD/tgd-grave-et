package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Interface {
	private Player player;
	public Interface(Player p) {
		this.player = p;
	}
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// Affichage du score
		g.setColor(Color.red);
		g.drawString(""+this.player.getScore(), 399, 10);
	}
	
}
