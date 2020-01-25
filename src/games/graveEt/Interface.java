package games.graveEt;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import app.AppFont;
import app.AppLoader;

public class Interface {
	private ArrayList<Player> players;
	private AppFont fontSmall;
	private AppFont fontBig;

	public Interface(ArrayList<Player> p) {
		this.players = p;

		fontSmall = AppLoader.loadFont("/fonts/vt323.ttf", AppFont.PLAIN, 24);
		fontBig = AppLoader.loadFont("/fonts/vt323.ttf", AppFont.BOLD, 42);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		// Affichage du score
		fontSmall.drawString(10, 10, "Score");
		fontBig.drawString(10, 30, Integer.toString(this.players.get(0).getScore()));

		int pos = container.getWidth() - 230;
		int points = this.players.get(0).getGravityPoint();
		fontSmall.drawString(pos, 10, "Points de gravite");
		Color col = points < 4 ? (points == 0 ? Color.black : Color.red) : Color.white;
		fontBig.drawString(pos, 38, Integer.toString(points), col);
		/*
		g.setColor(Color.white);
		for ( Player p : this.players) {
			g.drawString("Score de "+p.getName()+ " : " + p.getScore(), 399, 10);
			g.drawString("Nombre de points de Gravité restants à " + p.getName()+ " : " + p.getGravityPoint(), 700, 10);
		}
		*/

	}
}
