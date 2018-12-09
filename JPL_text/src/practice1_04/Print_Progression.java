package practice1_04;

public class Print_Progression {
	public static void main(String[] args) {
		int num = 0;
		
		while(num <= 10) {
			int square = num * num;
			System.out.println(num + "×" + num + " = " + square);
			num = num + 1;
		}
	}
}