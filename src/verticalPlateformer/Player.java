package verticalPlateformer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
	
	private int gravityPoint;
	private int horizontalSpeed;
	private int vertivalSpeed;
	private int horizontalAcceleration;
	private int vertivalAcceleration;
	private double posX;
	private double posY;
	private boolean leftPress, rightPress, downPress, qPress, dPress, sPress, zPress, spacePress ;
	
	public Player() {
	}
	
	public void update(GameContainer container, StateBasedGame game, int delta) {
		// Gestion des input du clavier :
		Input input = container.getInput ();
		boolean KEY_D = input.isKeyDown (Input.KEY_D);
		boolean KEY_Q = input.isKeyDown (Input.KEY_Q);
		boolean BUTTON_D = KEY_D && !KEY_D;
		boolean BUTTON_Q = KEY_Q && !KEY_Q;
		boolean KEY_Z = input.isKeyDown (Input.KEY_UP);
		boolean KEY_S= input.isKeyDown (Input.KEY_DOWN);
		boolean BUTTON_Z= KEY_Z && !KEY_S;
		boolean BUTTON_S= KEY_S && !KEY_Z;
		
		boolean KEY_LEFT = input.isKeyDown (Input.KEY_LEFT);
		boolean KEY_DOWN = input.isKeyDown (Input.KEY_DOWN);
		boolean KEY_RIGHT = input.isKeyDown (Input.KEY_RIGHT);
		boolean BUTTON_LEFT = KEY_LEFT && ! (KEY_DOWN || KEY_RIGHT);
		boolean BUTTON_DOWN = KEY_DOWN && ! (KEY_LEFT || KEY_RIGHT);
		boolean BUTTON_RIGHT = KEY_RIGHT && ! (KEY_LEFT || KEY_DOWN);
		
		boolean BUTTON_SPACE = input.isKeyDown(Input.KEY_SPACE);
		
		changeGravity(BUTTON_LEFT, BUTTON_DOWN, BUTTON_RIGHT);
		
		
	}
	

	public void changeGravity(boolean BUTTON_LEFT, boolean BUTTON_DOWN, boolean BUTTON_RIGHT) {
		if (gravityPoint > 0 ) {
			if (BUTTON_LEFT) {
//				super.changeGravity(-1);
			}
			else if (BUTTON_DOWN) {
//				super.changeGravity(0);
			}
			else if (BUTTON_RIGHT) {
//				super.changeGravity(1);
			}
		}
	}

}
