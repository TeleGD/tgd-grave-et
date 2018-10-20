package verticalPlateformer;

public class bonus{
	
	private double posx;
	private double posy;// coordonnees
	private int accHoriz;
	private int accVertic;//acceleration vertic et horiz
	
	private int val;//valeur bonus
	
	
	
	
	
	public bonus(double x,double y,int accx,int accy,int val) {
		this.posx=x;
		this.posy=y;
		this.accHoriz=accx;
		this.accVertic=accy;
		
		this.val=val;
	}
	
	public double getX() {
		return posx;
	}
	public double getY() {
		return posy;
	}
	public int getAccHoriz() {
		return accHoriz;
	}
	public int getAccVertic() {
		return accVertic;
	}
	
	
	public int getVal() {
		return val;
	}
	
	
}