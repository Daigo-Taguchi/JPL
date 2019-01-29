package practice14_03;

public class ThreadTest implements Runnable{
	private final int SUM_NUM = 10;
	private Sum sum;

	ThreadTest(Sum sum){
		this.sum = sum;
	}

	@Override
	public void run() {
		this.sum.sum(SUM_NUM);
	}
}
