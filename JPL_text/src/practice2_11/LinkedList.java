package practice2_11;

import practice2_10.Vehicle;

public class LinkedList {
	
	public Object object;
	public LinkedList nextAccess;
	
	public LinkedList(Vehicle vehicle) {
		this.object = vehicle;
	}
	
	public String toString() {
		String desc = this.object + "\n" + "---nextAccess--- " + "\n" + this.nextAccess;
		return desc;
	}
	
	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.vehicleID = 10;
		car.nowSpeed = 60.0;
		car.direction = 10.0;
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.vehicleID = 11;
		bike.nowSpeed = 30.0;
		bike.direction = 20.0;
		
		LinkedList list1 = new LinkedList(car);
		LinkedList list2 = new LinkedList(bike);
		
		list1.nextAccess = list2;
		list2.nextAccess = null;
		
		System.out.println(list1);
	}
}
