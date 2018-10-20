package verticalPlateformer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	
	private int score;
	private int horizontalSpeed;
	private int vertivalSpeed;
	private int horizontalAcceleration;
	private int vertivalAcceleration;
	private double posX;
	private double posY;
	private Image image;
	
	public Player() {
		try {
			image = new Image("images/verticalPlateformer/monstre.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.score = 0;
		
	}
	
	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde
		 *  1920-1080
		 *  1681-1727
		 *  
		 *  0-161
		 *  1681-1567
		 */
		context.setColor(Color.green);
		context.setLineWidth(2);
		context.drawImage(image, 0, 0, image.getWidth()*(container.getHeight()/image.getHeight()), container.getHeight(), 0, 0, image.getWidth()-1, image.getHeight()-1);
		context.drawOval(0, 161*container.getHeight()/image.getHeight(), 1681*container.getHeight()/image.getHeight(), 1567*container.getHeight()/image.getHeight());
	}
	
	public int getScore() {
		return this.score;
	}
	
	
	
}
