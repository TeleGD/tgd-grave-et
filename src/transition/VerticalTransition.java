package transition;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

/**
 * Vertical split transition that causes the previous state to split vertically
 * revealing the new state underneath.
 * 
 * This state is an enter transition.
 * 
 * @author kevin
 */
public class VerticalTransition implements Transition {
	/** The renderer to use for all GL operations */
	protected static SGL GL = Renderer.get();
	
	/** The previous game state */
	private GameState prev;
	/** The current offset */
	private float offset;
	/** True if the transition is finished */
	private boolean finish;
	/** The background to draw underneath the previous state (null for none) */
	private Color background;
	/** The image draw on top of the new screen during the transition */
	private Image image;
	
	/**
	 * Create a new transition
	 * 
	 * @param image The image to draw on top of the previous state
	 */
	public VerticalTransition(Image image) {
		this.image = image;
	}

	/**
	 * Create a new transition
	 * 
	 * @param background The background colour to draw under the previous state
	 * @param image The image to draw on top of the previous state
	 */
	public VerticalTransition(Color background, Image image) {
		this.background = background;
		this.image = image;
	}
	
	/**
	 * @see org.newdawn.slick.state.transition.Transition#init(org.newdawn.slick.state.GameState, org.newdawn.slick.state.GameState)
	 */
	public void init(GameState firstState, GameState secondState) {
		prev = secondState;
	}

	/**
	 * @see org.newdawn.slick.state.transition.Transition#isComplete()
	 */
	public boolean isComplete() {
		return finish;
	}

	/**
	 * @see org.newdawn.slick.state.transition.Transition#postRender(org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	public void postRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
		g.translate(0,(int) (container.getHeight()-offset+image.getHeight()));
		g.setClip(0,(int) (container.getHeight()-offset+image.getHeight()),container.getWidth(),container.getHeight());
		if (background != null) {
			Color c = g.getColor();
			g.setColor(background);
			g.fillRect(0,0,container.getWidth(),container.getHeight());
			g.setColor(c);
		}
		GL.glPushMatrix();
		prev.render(container, game, g);
		GL.glPopMatrix();
		g.clearClip();
		g.resetTransform();
		
		g.translate(0,(int) (container.getHeight()-offset));
		g.setClip(0,(int) (container.getHeight()-offset),container.getWidth(),image.getHeight());
//		if (background != null) {
//			Color c = g.getColor();
//			g.setColor(background);
//			g.fillRect(0,0,container.getWidth(),container.getHeight());
//			g.setColor(c);
//		}
		GL.glPushMatrix();
		g.drawImage(image, container.getWidth()/2-image.getWidth()/2, 0, container.getWidth()/2+image.getWidth()/2, image.getHeight(), 0, 0, image.getWidth(), image.getHeight());
		GL.glPopMatrix();
		g.clearClip();
		g.resetTransform();
	}

	/**
	 * @see org.newdawn.slick.state.transition.Transition#preRender(org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
	 */
	public void preRender(StateBasedGame game, GameContainer container, Graphics g) throws SlickException {
	}

	/**
	 * @see org.newdawn.slick.state.transition.Transition#update(org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.GameContainer, int)
	 */
	public void update(StateBasedGame game, GameContainer container, int delta)
			throws SlickException {
		offset += delta * 1f;
		if (offset > container.getHeight()+image.getHeight()) {
			finish = true;
		}
	}

}