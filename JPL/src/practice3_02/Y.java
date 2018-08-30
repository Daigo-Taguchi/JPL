package practice3_02;

public class Y extends X{
	protected int yMask = 0xf00;
	
	public Y() {
		fullMask |= yMask;
		System.out.printf("%x" , fullMask);
	}
	
	public static void main(String[] args) {
		//var x = new X();
		var y = new Y();
	}
}


