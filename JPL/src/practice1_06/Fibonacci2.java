package practice1_06;

public class Fibonacci2 {

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		int count = 0;
		String title = "---Fibonacci---";
		
		System.out.println(title);
		System.out.println("F"+ count + "：" + lo);
		while(hi < 50) {
			count++;
			System.out.println("F"+ count + "：" + hi);
			hi = lo + hi;
			lo = hi- lo;
		}

	}

}
