package verticalPlateformer;


import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.state.StateBasedGame;

import verticalPlateformer.plateforme.Plateforme;

public class Player extends Entity {


	private int gravityPoint;
	private int score;
	private static Image image;
	private Ellipse shape;
	private Circle background;
	private float width = 70;
	private float height = 70;
	private float baseSpeed = .24f;
	private float jumpSpeed = .48f;
	private float widthRelation;
	private float heightRelation;
	private float shapeWidth;
	private float shapeHeight;
	private float shapeStartHeight;
	private String name;
	private Plateforme plateforme;
	private static Image leftArrow;
	private static Image rightArrow;
	private static Image downArrow;
	
	static {
		try {
			leftArrow = new Image("images/verticalPlateformer/leftArrow.png");
			rightArrow = new Image("images/verticalPlateformer/rightArrow.png");
			downArrow = new Image("images/verticalPlateformer/downArrow.png");
			image = new Image("images/verticalPlateformer/monstre.png");
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
	
	public Player(String n,float posX, float posY) {
		super(posX, posY);
		gravityPoint = 10;
		widthRelation = width/image.getWidth();
		heightRelation = height/image.getHeight();
		shapeWidth = 1675*widthRelation;
		shapeHeight = 1567*heightRelation;
		shapeStartHeight = 161*heightRelation;

		this.name = n;
		this.score = 0;
		this.shape = new Ellipse(getPosX()+width/2, getPosY()+shapeStartHeight+(height-shapeStartHeight)/2, shapeWidth/2, shapeHeight/2);
		this.background = new Circle(posX+width/2, posY+width/2, (float) (1.5*width));
	}

	public String getName() {
		return name;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		// Gestion des input du clavier :
		Input input = container.getInput ();
		boolean BUTTON_SPACE = input.isKeyDown(Input.KEY_SPACE);

		if (BUTTON_SPACE && (plateforme != null)) {
			plateforme.setDestroyed(true);
			plateforme = null;
			this.gravityPoint+=1;
			super.jump (jumpSpeed);
		}
		
		if (plateforme == null) {
			changeGravity(input);
		}
		changeDirection(input);

		super.update(container, game, delta);
		shape.setLocation(getPosX(), getPosY()+shapeStartHeight);
		background.setCenterX(getPosX()+width/2);
		background.setCenterY(container.getHeight()/2+height/2);
		this.score = ((int) -this.getPosY())>score?(int) -this.getPosY():score;
	}

	public void render (GameContainer container, StateBasedGame game, Graphics context) {
		/* Méthode exécutée environ 60 fois par seconde */
		
		switch (this.getGravity()) {
		case 0:
			context.texture(background, downArrow);
			break;
		case 1:
			context.texture(background, rightArrow);
			break;
		case -1:
			context.texture(background, leftArrow);
			break;
		}
		
		/*  Ecran 1920-1080
		 *  Image :1681-1727
		 *
		 *  Début de la hitbox 0-161
		 *  Taille de la hitbox 1681-1567
		 */
		context.setColor(Color.green);
		context.setLineWidth(2);
		context.drawImage(image, getPosX(), container.getHeight() / 2, getPosX()+width, container.getHeight() / 2 + height, 0, 0, image.getWidth(), image.getHeight());
		//context.draw(shape);
		//context.drawOval(getPosX(), container.getHeight() / 2 + shapeStartHeight, shapeWidth, shapeHeight);
	}

	public void changeGravity(Input input) {
		boolean KEY_LEFT = input.isKeyDown (Input.KEY_LEFT);
		boolean KEY_DOWN = input.isKeyDown (Input.KEY_DOWN);
		boolean KEY_RIGHT = input.isKeyDown (Input.KEY_RIGHT);
		boolean BUTTON_LEFT = KEY_LEFT && ! (KEY_DOWN || KEY_RIGHT);
		boolean BUTTON_DOWN = KEY_DOWN && ! (KEY_LEFT || KEY_RIGHT);
		boolean BUTTON_RIGHT = KEY_RIGHT && ! (KEY_LEFT || KEY_DOWN);

		if (gravityPoint > 0 ) {
			if (BUTTON_LEFT && (getGravity() != -1)) {
				super.setGravity(-1);
				gravityPoint --;
				System.out.println("gravité  : -1");

			}
			else if (BUTTON_DOWN && (getGravity() != 0)) {
				super.setGravity(0);
				gravityPoint --;
				System.out.println("gravité  : 0");

			}
			else if (BUTTON_RIGHT && (getGravity() != 1)) {
				super.setGravity(1);
				gravityPoint --;
				System.out.println("gravité  : 1");

			}
		}
	}

	public void changeDirection(Input input) {
		boolean KEY_D = input.isKeyDown (Input.KEY_D);
		boolean KEY_Q = input.isKeyDown (Input.KEY_Q);
		boolean BUTTON_D = KEY_D && !KEY_Q;
		boolean BUTTON_Q = KEY_Q && !KEY_D;
		boolean KEY_Z = input.isKeyDown (Input.KEY_Z);
		boolean KEY_S= input.isKeyDown (Input.KEY_S);
		boolean BUTTON_Z= KEY_Z && !KEY_S;
		boolean BUTTON_S= KEY_S && !KEY_Z;

		super.setDirX(0);
		super.setDirY(0);

		if (plateforme == null) {
			if (BUTTON_Q &&  (getGravity() == 0)) {
				super.setDirX(-1 * baseSpeed );
			}
			else if (BUTTON_D &&  (getGravity()==0)){
				super.setDirX( 1* baseSpeed);
	
			}
			else if ( (getGravity() != 0)) {
				if (BUTTON_S) {
					super.setDirY( 1* baseSpeed);
	
				}
				else if (BUTTON_Z) {
					super.setDirY(-1* baseSpeed);
	
				}
			}
		} else {
			super.setDirX(super.getDirX() + plateforme.getSpeedX());
			super.setDirY(super.getDirY() + plateforme.getSpeedY());
		}
	}

	public int getScore() {
		return this.score;
	}

	public int getGravityPoint() {
		return gravityPoint;
	}
	
	public Ellipse getShape() {
		return shape;
	}

	public float getWidth() {
		return width;
	}

	public void setPlateforme(Plateforme plat) {
		this.plateforme= plat;
	}
	

}
