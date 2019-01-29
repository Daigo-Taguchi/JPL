package practice14_03;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Sum sum = new Sum();
		List<String> list = null;

		for(int i = 0; i < 1000; i ++) {
			int num = 10;
			new Thread(() -> {
				try {
					Thread.sleep(new Random().nextInt(10));
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				sum.sum(num);}).start();
			Collections.sort(list, (s1,s2) -> s1.length() - s2.length());
		}
	}

}
