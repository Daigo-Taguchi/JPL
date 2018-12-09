package practice1_12;

public class ImprovedFibonacci2 {
	private static final int MAX_INDEX = 10;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		String[] str = new String[MAX_INDEX];
		
		System.out.println("1: " + lo);
		for(int i = 2; i < MAX_INDEX; i++ ) {
			if(hi % 2 == 0) {
				mark = "*";
			}
			else {
				mark = "";
			}
			str[i] = i + ": " + hi + mark;
			System.out.println(str[i]);
			
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
