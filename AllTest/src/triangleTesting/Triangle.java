package triangleTesting;

public class Triangle {

	private double angle;
	private double zLocation;
	private double radians;
	
	public Triangle(double angle, double zLocation){
		this.angle = angle;
		this.zLocation = zLocation;
		radians = Math.toRadians(angle);
	}
	
	public double getAddY() {
		return (Math.sin(radians)* this.zLocation);
	}
	
	public double getAddX() {
		return(Math.cos(radians)*this.zLocation);
	}
}
