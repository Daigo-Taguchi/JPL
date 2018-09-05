package practice3_01;
import practice2_17.Vehicle;

public class PassengerVehicle extends Vehicle{
	private int cheerCount;
	private int nowPeople;
	
	public PassengerVehicle(String ownerName) {
		super(ownerName);
	}
	
	public void setCheerCount(int cheerCount) {
		this.cheerCount = cheerCount;
	}
	
	public int getCheerCount() {
		return this.cheerCount;
	}
	
	public void setNowPeople(int nowPeople) {
		this.nowPeople = nowPeople;
	}
	
	public int getNowPeople() {
		return this.nowPeople;
	}
	
	public String toString() {
		String desc = super.toString() + "座席数 = " + this.cheerCount + "\n" + "乗客人数 = " + this.nowPeople + "\n";
		return desc;
	}
	
	public static void main(String[] args) {
		var taxi = new PassengerVehicle("Taguchi");
		taxi.setNowSpeed(40.0);
		taxi.setDirection(80.0);
		taxi.setCheerCount(5);
		taxi.setNowPeople(3);
		System.out.println(taxi);
		
		var bus = new PassengerVehicle("Taguchi2");
		bus.setNowSpeed(50.0);
		bus.setDirection(70.0);
		bus.setCheerCount(10);
		bus.setNowPeople(5);
		System.out.println(bus);
	}
}