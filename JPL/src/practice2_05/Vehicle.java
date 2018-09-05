package practice2_05;

public class Vehicle {
	public double nowSpeed;
	public double direction;
	public String ownerName;

	public static int nextID = 0;
	public int vehicleID;

	public static void main(String[] args) {
		Vehicle car = new Vehicle();
		car.vehicleID = Vehicle.nextID;
		Vehicle.nextID++;
		car.nowSpeed = 60.0;
		car.direction = 10.0;
		car.ownerName = "Taguchi";
		
		System.out.println("nowSpeed = " + car.nowSpeed);
		System.out.println("direction = " + car.direction);
		System.out.println("ownewName = " + car.ownerName);
		System.out.println("ID番号 = " + car.vehicleID);
		System.out.println("次のID番号 = " + Vehicle.nextID);
		
		Vehicle bike = new Vehicle();
		bike.vehicleID = Vehicle.nextID;
		Vehicle.nextID++;
		bike.nowSpeed = 30.0;
		bike.direction = 20.0;
		bike.ownerName = "Taguchi2";
		
		System.out.println("===================");
		System.out.println("nowSpeed = " + bike.nowSpeed);
		System.out.println("direction = " + bike.direction);
		System.out.println("ownewName = " + bike.ownerName);
		System.out.println("ID番号 = " + bike.vehicleID);
		System.out.println("識次のID番号 = " + Vehicle.nextID);
		
	}
}
