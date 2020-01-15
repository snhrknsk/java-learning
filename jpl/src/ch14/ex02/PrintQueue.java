package ch14.ex02;

import java.util.ArrayList;
import java.util.List;

public class PrintQueue {

	private List<PrintJob> jobList = new ArrayList<>();
	public synchronized void add(PrintJob job) {
		jobList.add(job);
	}

	public synchronized PrintJob remove() {
		if (jobList.size() != 0) {
			return jobList.remove(0);
		}
		return null;
	}
}
