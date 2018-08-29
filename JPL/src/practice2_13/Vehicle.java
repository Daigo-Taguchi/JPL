package practice2_13;

public class Vehicle {
	private double nowSpeed;
	private double direction;
	private String ownerName;

	private static int nextID = 0;
	private int vehicleID;
	
	public Vehicle() {
		nextID++;
	}
	
	public Vehicle(String ownerName) {
		this();
		this.ownerName = ownerName;
	}
	
	public double getNowSpeed() {return this.nowSpeed;}
	public void setNowSpeed(double speed) {
		this.nowSpeed = speed;
	}
	
	public double getDirection() {return this.direction;}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public String getOwnerName() {return this.ownerName;}
	
	public int getNextID() {return nextID;}
	
	public int getVehicleID() {return this.vehicleID;}
	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}
	
	public static int ReturnMaxID() {
		return nextID;
	}
	
	public String toString() {
		String desc = "nowSpeed = " + this.nowSpeed + "%n" + "direction = " + this.direction + "%n" + "ownewName = " + this.ownerName + "%n" + "識別番号 = " + Vehicle.nextID + "%n" + "ID番号 = " + this.vehicleID + "%n" + "=========================" + "%n";
		return desc;
	}

	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.setVehicleID(10);
		car.setNowSpeed(60.0);
		car.setDirection(10.0);
		System.out.printf("carの参照先インスタンスの内容は%n" + car);
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.setVehicleID(11);
		bike.setNowSpeed(30.0);
		bike.setDirection(20.0);
		System.out.printf("bikeの参照先インスタンスの内容は%n" + bike);
	}
}