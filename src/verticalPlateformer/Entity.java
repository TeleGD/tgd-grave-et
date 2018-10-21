package verticalPlateformer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Entity {

	private static float GRAVITY;
	private static float MOVE;
	private static float JUMP;

	static {
		Entity.GRAVITY = .00144f;
		Entity.MOVE = .12f;
	}

	private boolean frozen;
	private int gravity;
	private float dirX;
	private float dirY;
	private float posX;
	private float posY;
	private float speedX;
	private float speedY;
	private float accX;
	private float accY;

	public Entity (float posX, float posY) {
		this.frozen = false;
		this.gravity = 0;
		this.dirX = 0;
		this.dirY = 0;
		this.posX = posX;
		this.posY = posY;
		this.speedX = 0f;
		this.speedY = 0f;
		this.accX = 0f;
		this.accY = Entity.GRAVITY;
	}

	public void update (GameContainer container, StateBasedGame game, int delta) {
		if (!this.frozen) {
			this.speedX += this.accX * delta / 2;
			this.speedY += this.accY * delta / 2;
		}
		this.posX += (this.speedX + this.dirX * Entity.MOVE) * delta;
		this.posY += (this.speedY + this.dirY * Entity.MOVE) * delta;
	}

	public abstract void render (GameContainer container, StateBasedGame game, Graphics context);

	public boolean isFrozen () {
		return this.frozen;
	}

	public void freeze () {
		if (!this.frozen) {
			this.frozen = true;
			this.speedX = 0f;
			this.speedY = 0f;
		}
	}

	public void unFreeze () {
		if (this.frozen) {
			this.frozen = false;
			switch (this.gravity) {
				case -1:
					this.speedX += Entity.JUMP;
					break;
				case 0:
					this.speedY -= Entity.JUMP;
					break;
				case 1:
					this.speedX -= Entity.JUMP;
			}
		}
	}

	public void setGravity (int gravity) {
		this.gravity = gravity;
		switch (gravity) {
			case -1:
				this.accX = -Entity.GRAVITY;
				this.accY = 0;
				break;
			case 0:
				this.accX = 0;
				this.accY = Entity.GRAVITY;
				break;
			case 1:
				this.accX = Entity.GRAVITY;
				this.accY = 0;
		}
	}

	public int getGravity () {
		return this.gravity;
	}

	public void setDirX (float f) {
		this.dirX = f;
	}

	public float getDirX () {
		return this.dirX;
	}

	public void setDirY (float y) {
		this.dirY = y;
	}

	public float getDirY () {
		return this.dirY;
	}

	public float getPosX () {
		return this.posX;
	}

	public float getPosY () {
		return this.posY;
	}

}
