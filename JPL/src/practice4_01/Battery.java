package practice4_01;

public class Battery implements EnergySource{
	public int batteryEnergy = 30;
	
	public boolean empty() {
		return batteryEnergy == 0;
	}
}
