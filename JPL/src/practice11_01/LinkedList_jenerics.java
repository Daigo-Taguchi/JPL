package practice11_01;

import practice4_01.Vehicle;

class LinkedList_jenerics<E> {
	private E element;
	private LinkedList_jenerics<E> nextAccess;//次のリスト
	private static int listCount = 0;//Listに保持する要素数のカウント
	
	public LinkedList_jenerics(E element){
		this.element = element;
		listCount++;
	}
	
	/**
	 * このクラスを直接出力した場合の処理を行う
	 * @return 現在のリストの中身と、次の要素の中身
	 */
	public String toString() {
		String desc = this.element + "\n" + "---nextAccess---" + "\n" + this.nextAccess;
		return desc;
	}
	
	/**
	 * 次のリストを指定する
	 * @param list
	 */
	public void setNextAccess(LinkedList_jenerics<E> list) {
		this.nextAccess =list;  
	}
	
	/**
	 * このクラスのLinkedListの要素数を返す
	 * @return 保持しているリストの数
	 */
	public int returnListCount() {
		return listCount;
	}
	
	public static void main(String[] args) {
		Vehicle car = new Vehicle("Taguchi");
		car.setNowSpeed(60.0);
		car.setDirection(10.0);
		
		Vehicle bike = new Vehicle("Taguchi2");
		bike.setNowSpeed(30.0);
		bike.setDirection(20.0);
		
		LinkedList_jenerics<Vehicle> list1 = new LinkedList_jenerics<Vehicle>(car);
		LinkedList_jenerics<Vehicle> list2 = new LinkedList_jenerics<Vehicle>(bike);
		
		list1.setNextAccess(list2);
		System.out.println(list1);
		
		list2.setNextAccess(null);
		System.out.println(list2);
		
		System.out.println("\n" + "現在の要素数は" + list2.returnListCount() + "です");
		
	}
}
