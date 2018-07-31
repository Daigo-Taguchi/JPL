package practice1_09;

public class Fibonacci3 {
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		int count = 0;
		
		int[] f = new int[10];
		f[0] = lo;

		System.out.println("F"+ count + "：" + f[count]);
		while(hi < 50) {
			count++;
			f[count] = hi;
			System.out.println("F"+ count + "：" + f[count]);
			hi = lo + hi;
			lo = hi- lo;
		}
		
		System.out.println("＊＊＊保存した配列の表示＊＊＊");
		for(int i = 0; i< f.length; i++ ) {
			System.out.println("f[" + i + "] = " + f[i]);
		}
	}
}