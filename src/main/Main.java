package main;

import consumer.Consumer;
import consumer.ConsumerForce;
import gui.GUI;
import gui.GUIFacade;
import item.ItemBuffer;
import logg.Logg;
import worker.Workforce;

public class Main {

	public static void main(String[] args) {
		boolean isRunning = true;
		String path = "src/Files/logg.txt";
		ItemBuffer buffer = new ItemBuffer();
		Workforce workForce = new Workforce();
		ConsumerForce consumerForce = new ConsumerForce(buffer);
		Logg logg = Logg.getLogg();
		GUIFacade guiFacade = new GUIFacade(buffer, workForce, logg, path);
		GUI gui = new GUI(guiFacade);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui.createAndShowGUI();
			}
		});

		Thread conSumerForceThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isRunning) {
					consumerForce.removeAllConsumers();

					int amountOfConsumers = (int) (Math.random() * 12 + 1 + 3);

					for (int i = 0; i < amountOfConsumers; i++) {
						consumerForce.addConsumer(new Consumer(buffer));
					}
					for (Consumer consumer : consumerForce.consumerQueue) {
						Thread t = new Thread(consumer);
						t.start();
					}
					try {
						System.out.println(consumerForce.getLength());
						Thread.sleep(6000);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		conSumerForceThread.start();

		Thread itemLoggThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					logg.writeData("Items in stock: " + String.valueOf(buffer.getSize()), path);
					;
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		itemLoggThread.start();

	}

}
