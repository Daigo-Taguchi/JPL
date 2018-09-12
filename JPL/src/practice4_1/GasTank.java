package practice4_1;

public class GasTank extends EnergySource{
	public int gasEnergy;
	
	public boolean empty() {
		return gasEnergy == 0 ;
	}

}
