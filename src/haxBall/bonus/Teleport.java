package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public class Teleport extends Bonus {
	private boolean activated, deleted;
	private Ball ball;
	private Field field;

	public Teleport(int posX, int posY, double fieldWidth, Ball ball, Field field) {
		super(posX, posY, new Color(200,100,100), fieldWidth);
		
		this.field = field;
		this.ball = ball;		
		activated = false;
		deleted = false;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		
	}

	public boolean isActivated() {
		return activated;
	}

	public void activate(Player p, Ball b) {
		activated = true;
		
		int posX = (int)(Math.random()*field.getWidth()/6);
		int posY = (int)(Math.random()*field.getHeight()/2) + field.getPosY() + field.getHeight()/4;
		System.out.println("test");
		if(p.getID() == 1) {
			posX += field.getPosX() + field.getWidth()/6;
			
		} else {
			posX += field.getPosX() + field.getWidth()/6 + field.getWidth()/2; 
		}
		
		ball.setPosX(posX);
		ball.setPosY(posY);
		
		deleted = true;
	}

	public boolean isDeleted() {
		return deleted;
	}
	
}
