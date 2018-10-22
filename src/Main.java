import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JOptionPane;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public final class Main {

	public static final void main (String [] arguments) throws SlickException {
		String title = "CN-20-10";
		Object [] options = {
			"Oui",
			"Non"
		};
		int returnValue = JOptionPane.showOptionDialog (
			null,
			"Voulez-vous jouer en plein écran ?",
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options [0]
		);
		StateBasedGame game = new StateBasedGame (title) {

			@Override
			public void initStatesList (GameContainer container) {
				this.addState (new pages.Welcome (0));
				this.addState (new pages.Choice (1));
				this.addState (new pages.Pause (2, 4));
				this.addState (new pages.Pause (3, 5));
				this.addState (new haxBall.World (4));
				this.addState (new verticalPlateformer.World (5));
				this.addState (new verticalPlateformer.DeathPage (6));
			}

		};
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		AppGameContainer container = returnValue == 0 ? new AppGameContainer (game,  gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight(), true) : new AppGameContainer (game, 1280, 720, false);
		container.setTargetFrameRate (60);
		container.setVSync (true);
		container.setShowFPS (false);
		container.start ();
	}

}
