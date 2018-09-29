package practice4_01;

public class GasTank implements EnergySource{
	public int gasEnergy = 20;
	
	public boolean empty() {
		return gasEnergy == 0 ;
	}

}
