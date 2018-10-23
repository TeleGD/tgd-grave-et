package verticalPlateformer;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Interface {
	private ArrayList<Player> players;
	private Color color = new Color(0x001e3514);
	
	public Interface(ArrayList<Player> p) {
		this.players = p;
	}
	
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, container.getWidth(), container.getHeight());
		// Affichage du score
		g.setColor(Color.white);
		for ( Player p : this.players) {
			g.drawString("Score de "+p.getName()+ " : " + p.getScore(), 399, 10);
			g.drawString("Nombre de points de Gravité restants à " + p.getName()+ " : " + p.getGravityPoint(), 700, 10);
		}
	}
}