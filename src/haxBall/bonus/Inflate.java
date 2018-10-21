package haxBall.bonus;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import haxBall.Ball;
import haxBall.Field;
import haxBall.Player;

public class Inflate extends Bonus {
	private Ball ball;
	private int rad;
	private int timer;
	
	public Inflate(int posX, int posY, Field field, Ball ball) {
		super(posX, posY, new Color(100,200,100), field);
		
		this.ball = ball;		
		this.rad = ball.getRad();
		timer = 10*1000;
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		if(!deleted && activated) {
			timer -= delta;
		}
		
		if (timer <= 0) {
			ball.setRad(rad);
			deleted = true;
		}
	}

	public void activate(Player p, Ball b) {
		activated = true;
		
		ball.setRad(rad*2);
		
		deleted = true;
	}
}
