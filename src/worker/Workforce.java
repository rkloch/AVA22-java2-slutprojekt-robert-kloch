//Collection of workers

package worker;

import java.util.LinkedList;
import java.util.Queue;

public class Workforce {
	Queue<Worker> workerQueue = new LinkedList<Worker>();

	public void addWorker(Worker worker) {
		workerQueue.add(worker);

	}

	public Worker removeWorker() {
		if (!workerQueue.isEmpty())
			return workerQueue.remove();
		else {
			return null;
		}

	}

	public int getLength() {
		return workerQueue.size();
	}

}
