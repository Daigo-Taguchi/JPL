package practice2_14;

import practice2_13.Vehicle;

public class LinkedList {
	
	private Object object;
	private LinkedList nextAccess;
	
	public LinkedList(Vehicle vehicle) {
		this.object = vehicle;
	}
	
	public String toString() {
		String desc = this.object + "\n" + "---nextAccess--- " + "\n" + this.nextAccess;
		return desc;
	}
	
	public void setNextAccess(LinkedList nextAccess) {
		this.nextAccess = nextAccess;
	}
	
	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.setNowSpeed(60.0);
		car.setDirection(10.0);
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.setNowSpeed(30.0);
		bike.setDirection(20.0);
		
		LinkedList list1 = new LinkedList(car);
		LinkedList list2 = new LinkedList(bike);
		
		list1.setNextAccess(list2);
		list2.setNextAccess(null);
		
		System.out.println(list1);
	}
}
