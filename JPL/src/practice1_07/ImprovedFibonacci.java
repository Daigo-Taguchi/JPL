package practice1_07;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args)
	{
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.println("F0：" + lo);
		for(int i =  MAX_INDEX; i > 0 ; i--) {
			if(hi % 2 == 0) {
				mark = "*";
			}
			else {
				mark = "";
			}
			
			System.out.println(i + "：" + hi + mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}