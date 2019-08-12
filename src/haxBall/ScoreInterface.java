package haxBall;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ScoreInterface {
	private Ball ball;
	private Image scoreball;
	private boolean win;

	{
		try {
			scoreball = new Image("images/haxball/score/ball.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ScoreInterface(Ball b) {
		this.ball = b;
		this.win = false;
	}

		public void render(GameContainer container, StateBasedGame game, Graphics context){
			switch(ball.getPointsJ1()) {
				case 1:
					context.drawImage(scoreball, 399, 25,Color.blue);
					break ;
				case 2:
					context.drawImage(scoreball, 449, 25,Color.blue);
					context.drawImage(scoreball, 399, 25,Color.blue);
					break ;
				case 3:
					context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		break ;
	case 4:
		context.drawImage(scoreball, 549, 25,Color.blue);
		context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		break ;
	case 5:
		context.drawImage(scoreball, 549, 25,Color.blue);
		context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		context.drawImage(scoreball, 599, 25,Color.blue);
		break ;
	case 6:
		context.drawImage(scoreball, 649, 25,Color.blue);
		context.drawImage(scoreball, 549, 25,Color.blue);
		context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		context.drawImage(scoreball, 599, 25,Color.blue);
		break ;
	case 7:
		context.drawImage(scoreball, 649, 25,Color.blue);
		context.drawImage(scoreball, 549, 25,Color.blue);
		context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		context.drawImage(scoreball, 599, 25,Color.blue);
		context.drawImage(scoreball, 349, 25,Color.blue);
		break ;
	case 8:
		context.drawImage(scoreball, 649, 25,Color.blue);
		context.drawImage(scoreball, 549, 25,Color.blue);
		context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		context.drawImage(scoreball, 599, 25,Color.blue);
		context.drawImage(scoreball, 349, 25,Color.blue);
		context.drawImage(scoreball, 299, 25,Color.blue);
		break ;
	case 9:
		context.drawImage(scoreball, 699, 25,Color.blue);
		context.drawImage(scoreball, 649, 25,Color.blue);
		context.drawImage(scoreball, 549, 25,Color.blue);
		context.drawImage(scoreball, 449, 25,Color.blue);
		context.drawImage(scoreball, 499, 25,Color.blue);
		context.drawImage(scoreball, 399, 25,Color.blue);
		context.drawImage(scoreball, 599, 25,Color.blue);
		context.drawImage(scoreball, 349, 25,Color.blue);
		context.drawImage(scoreball, 299, 25,Color.blue);
		break ;
	case 10:
		win = true;
		break ;
	}
	switch(ball.getPointsJ2()) {
	case 1:
		context.drawImage(scoreball, 999, 25,Color.red);
		break ;
	case 2:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		break ;
	case 3:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		break ;
	case 4:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 1099, 25,Color.red);
		break ;
	case 5:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 1099, 25,Color.red);
		context.drawImage(scoreball, 1149, 25,Color.red);
		break ;
	case 6:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		context.drawImage(scoreball, 1149, 25,Color.red);
		context.drawImage(scoreball, 1199, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 1099, 25,Color.red);
		break ;
	case 7:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		context.drawImage(scoreball, 1149, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 1099, 25,Color.red);
		context.drawImage(scoreball, 1199, 25,Color.red);
		context.drawImage(scoreball, 1249, 25,Color.red);
		break ;
	case 8:
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		context.drawImage(scoreball, 1149, 25,Color.red);
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 1099, 25,Color.red);
		context.drawImage(scoreball, 1199, 25,Color.red);
		context.drawImage(scoreball, 1249, 25,Color.red);
		context.drawImage(scoreball, 1299, 25,Color.red);
		break ;
	case 9:
		context.drawImage(scoreball, 999, 25,Color.red);
		context.drawImage(scoreball, 949, 25,Color.red);
		context.drawImage(scoreball, 1049, 25,Color.red);
		context.drawImage(scoreball, 1149, 25,Color.red);
		context.drawImage(scoreball, 1099, 25,Color.red);
		context.drawImage(scoreball, 1199, 25,Color.red);
		context.drawImage(scoreball, 1299, 25,Color.red);
		context.drawImage(scoreball, 1249, 25,Color.red);
		context.drawImage(scoreball, 1349, 25,Color.red);
		break ;
	case 10:
		win = true;
		break ;
	}
	}
	public boolean isWin() {
		return win;
	}
}
