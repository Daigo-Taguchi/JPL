package practice1_03;

public class Fibonacci {

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		int count = 0;
		
		System.out.println("F"+ count + ":" + lo);
		while(hi < 50) {
			count++;
			System.out.println("F"+ count + ":" + hi);
			hi = lo + hi;
			lo = hi- lo;
		}
	}
}
