package practice3_06;

public class Battery extends EnergySource{
	public int batteryEnergy;
	
	public boolean empty() {
		return batteryEnergy == 0;
	}
}
