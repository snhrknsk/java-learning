package ch14.ex02;


public class PrintServer implements Runnable {

	private final PrintQueue requests = new PrintQueue();
	private final long threadId;

	public PrintServer() {
		Thread printServer = new Thread(this);
		threadId = printServer.getId();
		printServer.start();
	}

	public void print(PrintJob job) {
		requests.add(job);
	}

	@Override
	public void run() {
		if (threadId != Thread.currentThread().getId()) {
			throw new IllegalStateException("コンストラクタで生成されたThreadではありません");
		}
		for(;;)
			realPrint(requests.remove());
	}

	public void realPrint(PrintJob job) {

	}

}
