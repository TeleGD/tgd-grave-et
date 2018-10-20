package verticalPlateformer;

public class DeathLine {
	
	private int posX;
	private int posY;
	private int speed;
	private int accel;
	
	public DeathLine() {
		
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getAccel() {
		return this.accel;
	}
	
	public void setPosX(int x) {
		this.posX = x;
	}
	
	public void setPosY(int y) {
		this.posY = y;
	}
	
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public void setAccel(int a) {
		this.accel = a;
	}
}
