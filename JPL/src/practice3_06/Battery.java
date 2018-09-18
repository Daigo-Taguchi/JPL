package practice3_06;

public class Battery extends EnergySource{
	private int batteryEnergy = 30;
	
	public boolean empty() {
		return batteryEnergy == 0;
	}
}
