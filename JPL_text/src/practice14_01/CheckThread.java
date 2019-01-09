package practice14_01;

public class CheckThread {

	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		String threadName = thread.getName();
		System.out.println(threadName);
	}

}
