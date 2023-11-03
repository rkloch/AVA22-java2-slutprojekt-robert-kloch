package worker;

import javax.crypto.BadPaddingException;

import item.Item;
import item.ItemBuffer;

public class Worker implements Runnable {
	ItemBuffer buffer = null;
	int interval;

	public boolean isRunning = true;

	public Worker(ItemBuffer buffer) {
		this.buffer = buffer;
		//Random interval for production between 1-10
		interval = (int) Math.floor((Math.random() * 10) + 1);
	}

	@Override
	public void run() {

		while (isRunning) {
			try {
				//Adding Item with random id
				buffer.add(new Item((int) (Math.random() * 1000)));
				System.out.println("added item. item size: " + buffer.getSize());
				Thread.sleep(interval * 1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void stop() {
		isRunning = false;
	}

	public String getInterval() {
		return String.valueOf(interval);
	}

}
