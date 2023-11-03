package item;

import java.util.LinkedList;
import java.util.Queue;

public class ItemBuffer {
	Queue<Item> itemBuffer = new LinkedList<Item>();
	int maxItem = 300;

	
	//Starting with 50% stocked inventory
	public ItemBuffer() {
		for (int i = 0; i < maxItem / 2; i++) {
			add(new Item(i));
		}
	}

	public synchronized void add(Item item) {
		if (getSize() < maxItem)
			itemBuffer.add(item);
		notify();

	}

	public synchronized Item remove() {
		if (itemBuffer.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return itemBuffer.remove();
	}

	public int getSize() {
		return itemBuffer.size();
	}

	public int getMaxSize() {
		return maxItem;
	}

}
