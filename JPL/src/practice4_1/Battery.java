package practice4_1;

public class Battery implements EnergySource{
	public int batteryEnergy = 30;
	
	public boolean empty() {
		return batteryEnergy == 0;
	}
}
