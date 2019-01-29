package practice14_02;

public class PrintServer implements Runnable{
	private final PrintQueue requests = new PrintQueue();
	private Thread workerThread;

	public PrintServer() {
		this.workerThread = new Thread(this);
		this.workerThread.start();
	}

	@Override
	public void run() {
		if(Thread.currentThread() == workerThread) {
			for(;;) {
				// realPrint(requests.remove());
			}
		}
		else {
			System.out.println("異なるThreadからrunが実行されました");
		}
	}

	/***
	 * 印刷の実際の処理を行う
	 * @param job
	 */
	private void realPrint(PrintJob job) {

	}

}
