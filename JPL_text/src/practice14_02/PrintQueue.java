package practice14_02;

import java.util.ArrayDeque;
import java.util.Queue;

public class PrintQueue {
	private Queue<PrintJob> queue = new ArrayDeque<>();
	
	public void add(PrintJob job) {
		this.queue.add(job);
	}
	
	public PrintJob remove() {
		PrintJob saveJob = queue.poll();
		this.queue.remove();
		return saveJob;
	}
}
