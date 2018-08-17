package practice2_6;

import practice2_5.Vehicle;

public class LinkedList {
	public Object object;
	public LinkedList nextAccess;

	public static void main(String[] args) {
		Vehicle car = new Vehicle();
		Vehicle bike = new Vehicle();
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		LinkedList list3 = new LinkedList();
		
		list1.object = car;
		list1.nextAccess = list2;
		
		list2.object = bike;
		list2.nextAccess = list3; 
	}

}
