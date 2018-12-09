package practice1_09;

public class Fibonacci3 {
	static final int MAX_INDEX = 10;
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		
		int[] f = new int[MAX_INDEX];
		f[0] = lo;

		System.out.println("F0；" + f[0]);
		
		for(int i = 1; i< MAX_INDEX; i++) {
			f[i] = hi;
			System.out.println("F" + i + ":" + f[i]);
			hi = lo + hi;
			lo = hi - lo;
		}
		
		
		System.out.println("＊＊＊保存した配列の表示＊＊＊");
		for(int i = 0; i< f.length; i++ ) {
			System.out.println("f[" + i + "] = " + f[i]);
		}
	}
}