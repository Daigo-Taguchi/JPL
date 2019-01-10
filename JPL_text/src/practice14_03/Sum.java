package practice14_03;

public class Sum {
	private int num = 0;

	/***
	 * 保持している数値に引数の数値を加算して表示するメソッド
	 * @param num
	 */
	public synchronized void sum(int num) {
		this.num += num;
		System.out.println(this.num);
	}
}
