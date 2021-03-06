package practice2_10;

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
	
	public String toString() {
		String desc = "nowSpeed = " + this.nowSpeed + "\n" + "direction = " + this.direction + "\n" + "ownewName = " + this.ownerName + "\n" + "ID番号 = " + this.vehicleID + "\n" + "次のID番号 = " + Vehicle.nextID + "\n" + "ID番号の最大値 = " + Vehicle.returnUsedMaxID() + "\n" +"=========================" ;

		return desc;
		
	}

	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.nowSpeed = 60.0;
		car.direction = 10.0;
		
		System.out.println("carの参照先インスタンスの内容は" + "\n" + car);
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.nowSpeed = 30.0;
		bike.direction = 20.0;
		System.out.println("bikeの参照先インスタンスの内容は" + "\n"+ bike);
		
	}
}