package practice1_10;

public class ImprovedFibonacci {
	private static final int MAX_INDEX = 10;
	
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		
		Save[] save = new Save[MAX_INDEX];
		
		for(int i = 0; i<MAX_INDEX; i++) { //なんでこれがないとヌルポ起こるの？ひとつずつnewしないといけないってこと？
			save[i] = new Save();
		}
		
		save[0].fib = lo;
		save[0].bool = false; 
		mark = "";
		
		System.out.println("F0： " + save[0].fib + mark);
		
		for(int i = 1; i < MAX_INDEX; i++) {
			if(hi % 2 == 0) {
				mark = "*";
				save[i].bool = true;
			}
			else {
				mark = "";
				save[i].bool = false;
			}
			System.out.println("F" +  i + "：" + hi + mark);
			save[i].fib = hi;
			
			hi = lo + hi;
			lo = hi - lo; 
		}
		System.out.println("");
		System.out.println("***Saveクラスに保存したデータの出力***");
		
		for(int i = 0; i < MAX_INDEX; i++) {
			System.out.println("save[" + i + "]:" + save[i].fib + "," + save[i].bool);
		}
	}
}
