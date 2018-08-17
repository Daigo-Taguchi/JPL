package practice2_3;

public class Vehicle {
	public double nowSpeed;
	public double direction;
	public String ownerName;
	
	/*finalにするべきである。
	 * 理由：idは識別子であり最初に決定してから変更することはないため。
	 * むしろ変更されてはidの意味がない。*/
	public static final  int id = 1234;
}
