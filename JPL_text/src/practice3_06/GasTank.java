package practice3_06;

public class GasTank extends EnergySource{
	private int gasEnergy = 30;
	
	public boolean empty() {
		return gasEnergy == 0 ;
	}

}
