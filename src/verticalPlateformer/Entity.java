package verticalPlateformer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;

public abstract class Entity {

	private static float GRAVITY;
	private static float MOVE;

	static {
		Entity.GRAVITY = 720f;
		Entity.MOVE = 120f;
	}

	private int gravity;
	private int dirX;
	private int dirY;
	private float posX;
	private float posY;
	private float speedX;
	private float speedY;
	private float accX;
	private float accY;

	public Entity (float posX, float posY) {
		this.gravity = 0;
		this.dirX = 0;
		this.dirY = 0;
		this.posX = posX;
		this.posY = posY;
		this.speedX = 0f;
		this.speedY = 0f;
		this.accX = 0;
		this.accY = -Entity.GRAVITY;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		this.speedX += this.accX * delta;
		this.speedY += this.accY * delta;
		this.posX += (this.speedX + this.dirX * Entity.MOVE) * delta;
		this.posY += (this.speedY + this.dirY * Entity.MOVE) * delta;
	}

	public abstract void render (GameContainer container, StateBasedGame game, Graphics context);

	public void setGravity (int gravity) {
		this.gravity = gravity;
		switch (gravity) {
			case -1:
				this.accX = -Entity.GRAVITY;
				this.accY = 0;
			case 0:
				this.accX = 0;
				this.accY = -Entity.GRAVITY;
			case 1:
				this.accX = Entity.GRAVITY;
				this.accY = 0;
		}
	}

	public int getGravity () {
		return this.gravity;
	}

	public void setDirX (int x) {
		this.dirX = x;
	}

	public void setDirY (int y) {
		this.dirY = y;
	}

	public float getPosX () {
		return this.posX;
	}

	public float getPosY () {
		return this.posY;
	}

}
