package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public class Teleport extends Bonus {
	private Ball ball;
	private Sound sound;

	public Teleport(int posX, int posY, Field field, Ball ball) {
		super(posX, posY, new Color(0,255,255), field);

		this.ball = ball;
		try {
			this.sound = new Sound("res/sound/haxBall/teleportation.ogg");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		super.update(container, game, delta);
	}

	public void activate(Player p, Ball b) {
		activated = true;
		
		int posX = (int)(Math.random()*field.getWidth()/6);
		int posY = (int)(Math.random()*field.getHeight()/2) + field.getPosY() + field.getHeight()/4;
		if(p.getID() == 1) {
			posX += field.getPosX() + field.getWidth()/6;
			
		} else {
			posX += field.getPosX() + field.getWidth()/6 + field.getWidth()/2; 
		}
		
		ball.setPosX(posX);
		ball.setPosY(posY);
		
		deleted = true;
		sound.play(1, (float) 0.4);
	}
	
}
