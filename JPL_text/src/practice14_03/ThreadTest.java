package practice14_03;

public class ThreadTest implements Runnable{
	private final int SUM_NUM = 10;
	
	ThreadTest(){
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		Sum sum =new Sum();
		sum.sum(SUM_NUM);
	}
}
