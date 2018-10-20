package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Field {
	private double height;
	private double width;
	private double pos_x;
	private double pos_y;
	private Color color;
	
	public Field(int world_height , int world_width){
		// normalement ca marche pas...
		height = 0.6 * world_height;
		width = 0.6 * world_width;
		pos_x = 0.2 * world_height;
		pos_y = 0.2 * world_width;
		
		// creation des joueurs à faire...
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void update (GameContainer container, StateBasedGame game, int delta) {
		/* Méthode exécutée environ 60 fois par seconde */
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde on espère !  */
		setColor(Color.green);
		context.fillRect(0,0,100,100);
	}
}
