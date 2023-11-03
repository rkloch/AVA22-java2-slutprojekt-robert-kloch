package consumer;

import item.ItemBuffer;

public class Consumer implements Runnable {
	ItemBuffer buffer;
	int interval;

	boolean isRunning = true;

	public Consumer(ItemBuffer buffer) {
		this.buffer = buffer;
		// Random interval of production between 1 - 10
		interval = (int) Math.floor((Math.random() * 10) + 1);
	}

	@Override
	public void run() {

		while (isRunning) {
			try {
				Thread.sleep(interval * 1000);
				buffer.remove();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void stop() {
		isRunning = false;
	}

}
