package practice2_5;

public class Vehicle {
	public double nowSpeed;
	public double direction;
	public String ownerName;

	/*finalにするべきである。
	 * 理由：idは識別子であり最初に決定してから変更することはないため。
	 * むしろ変更されてはidの意味がない。*/
	public static final  int id = 1234;

	public static void main(String[] args) {
		Vehicle car = new Vehicle();
		car.nowSpeed = 60.0;
		car.direction = 10.0;
		car.ownerName = "Taguchi";
		
		Vehicle bike = new Vehicle();
		bike.nowSpeed = 30.0;
		bike.direction = 20.0;
		bike.ownerName = "Taguchi2";
		
		System.out.println(car.nowSpeed);
		System.out.println(car.direction);
		System.out.println(car.ownerName);
		System.out.println("===================");
		System.out.println(bike.nowSpeed);
		System.out.println(bike.direction);
		System.out.println(bike.ownerName);
	}
}
