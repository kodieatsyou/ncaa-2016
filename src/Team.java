

public class Team {
	String name = null;
	double winPercent = 0.00;
	int seed = 0;
	String region = null;
	
	public Team(String name, double winPercent, String region, int seed){
		this.name = name;
		this.winPercent = winPercent;
		this.region = region;
		this.seed = seed;
	}
	
	public void info() {
		System.out.println("Name:" + name + " " + "Percent:" + winPercent + " " + "Region:" + region + " " + "Seed:" + seed);
	}
}
