package consumer;

import java.util.LinkedList;
import java.util.Queue;

import item.ItemBuffer;

//Collection of Consumers
public class ConsumerForce {
	public Queue<Consumer> consumerQueue = new LinkedList<Consumer>();

	public ConsumerForce(ItemBuffer buffer) {

	}

	public void addConsumer(Consumer consumer) {
		consumerQueue.add(consumer);

	}

	public Consumer removeConsumer() {
		if (!consumerQueue.isEmpty())
			return consumerQueue.remove();
		else
			return null;

	}

	public void removeAllConsumers() {
		consumerQueue.removeAll(consumerQueue);
	}

	public int getLength() {
		return consumerQueue.size();
	}
}
