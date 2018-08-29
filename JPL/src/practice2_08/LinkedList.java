package practice2_08;

import practice2_07.Vehicle;

public class LinkedList {
	
	public Object object;
	public LinkedList nextAccess;

	public LinkedList() {
	}
	
	public LinkedList(Vehicle vehicle) {
		this.object = vehicle;
	}
	
	public static void main(String[] args) {
		Vehicle car = new Vehicle();
		Vehicle bike = new Vehicle();
		LinkedList list1 = new LinkedList(car);
		LinkedList list2 = new LinkedList(bike);
		LinkedList list3 = new LinkedList();
		
		list1.nextAccess = list2;
		list2.nextAccess = list3;
	}
}
