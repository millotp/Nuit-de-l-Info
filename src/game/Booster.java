package game;

public class Booster {
	private double startAngle;
	private double endAngle;
	boolean boost;
	
	public Booster(double startAngle, double endAngle, boolean boost) {
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.boost = boost;
	}
	
	public boolean getBoost() {
		return boost;
	}
	
	
}
