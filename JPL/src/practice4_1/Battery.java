package practice4_1;

public class Battery extends EnergySource{
	public int batteryEnergy;
	
	public boolean empty() {
		return batteryEnergy == 0;
	}
}
