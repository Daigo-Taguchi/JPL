package practice2_06;

import practice2_05.Vehicle;

public class LinkedList {
	public Object object;
	public LinkedList nextAccess;

	public static void main(String[] args) {
		Vehicle car = new Vehicle();
		Vehicle bike = new Vehicle();
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		
		list1.object = car;
		list1.nextAccess = list2;
		
		list2.object = bike;
		list2.nextAccess = null;
	}

}
