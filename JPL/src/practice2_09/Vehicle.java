package practice2_09;

public class Vehicle {
	public double nowSpeed;
	public double direction;
	public String ownerName;

	public static int nextID = 0;
	public int vehicleID;
	
	public Vehicle() {
		vehicleID = nextID;
		nextID++;
	}
	
	public Vehicle(String ownerName) {
		this();
		this.ownerName = ownerName;
	}
	
	public static int returnUsedMaxID() {
		return nextID - 1;
	}

	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.nowSpeed = 60.0;
		car.direction = 10.0;
		
		System.out.println("nowSpeed = " + car.nowSpeed);
		System.out.println("direction = " + car.direction);
		System.out.println("ownewName = " + car.ownerName);
		System.out.println("ID番号 = " + car.vehicleID);
		System.out.println("次のID番号 = " + Vehicle.nextID);
		System.out.println("ID番号の最大値 = " + Vehicle.returnUsedMaxID());
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.nowSpeed = 30.0;
		bike.direction = 20.0;
		
		System.out.println("===================");
		System.out.println("nowSpeed = " + bike.nowSpeed);
		System.out.println("direction = " + bike.direction);
		System.out.println("ownewName = " + bike.ownerName);
		System.out.println("ID番号 = " + bike.vehicleID);
		System.out.println("次のID番号 = " + Vehicle.nextID);
		System.out.println("ID番号の最大値 = " + Vehicle.returnUsedMaxID());
		
	}
}