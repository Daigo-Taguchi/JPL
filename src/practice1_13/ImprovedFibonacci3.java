package practice1_13;

public class ImprovedFibonacci3 {
	private static final int MAX_INDEX = 10;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		String[] str = new String[MAX_INDEX];
		
		System.out.printf("1: " + lo + "%n");
		for(int i = 2; i < MAX_INDEX; i++ ) {
			if(hi % 2 == 0) {
				mark = "*";
			}
			else {
				mark = "";
			}
			str[i] = i + ": " + hi + mark;
			System.out.printf(str[i] + "%n");
			
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
