package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GUI extends JPanel implements ActionListener {
	JProgressBar progressBar;
	JButton plusButton;
	JButton minusButton;
	JButton saveButton;
	JButton loadButton;
	JTextArea loggTextArea;
	boolean hasBeenWarned = false;
	boolean isRunning = true;

	public GUI(GUIFacade guiFacade) {
		super(new BorderLayout());

		plusButton = new JButton("+");
		plusButton.setActionCommand("+");
		plusButton.addActionListener(e -> guiFacade.addWorker());

		minusButton = new JButton("-");
		minusButton.setActionCommand("-");
		minusButton.addActionListener(e -> guiFacade.removeWorker());

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setBorder(new EmptyBorder(2, 2, 2, 2));

		loggTextArea = new JTextArea(5, 20);
		loggTextArea.setMargin(new Insets(5, 5, 5, 5));
		loggTextArea.setEditable(false);
		loggTextArea.setLineWrap(true);
		loggTextArea.setWrapStyleWord(true);

		JPanel panel = new JPanel();
		panel.add(plusButton);
		panel.add(minusButton);

		add(panel, BorderLayout.PAGE_END);
		add(new JScrollPane(loggTextArea), BorderLayout.CENTER);
		add(new JScrollPane(progressBar), BorderLayout.PAGE_START);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				while (isRunning) {
					
					
					progressBar.setValue(guiFacade.percentInStock());
					loggTextArea.setText(guiFacade.getLogg());
					
					//Check if stock is too high or to low
					//Change color depending on conditions
					if (!guiFacade.checkItemStockLow() && !guiFacade.checkItemStockHigh()) {
						panel.setBackground(new Color(0, 255, 0));
						hasBeenWarned = false;

					} else {
						if (guiFacade.checkItemStockLow() && !hasBeenWarned) {
							guiFacade.logg.writeData("Warning! Stock to low, hire new workers", guiFacade.path);
						} else if (!hasBeenWarned) {
							guiFacade.logg.writeData("Warning! Stock to high, fire workers", guiFacade.path);
						}
						panel.setBackground(new Color(255, 0, 0));
						hasBeenWarned = true;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();

	}

	public void actionPerformed(ActionEvent evt) {

	}

	public void createAndShowGUI() {
		JFrame frame = new JFrame("Production Regulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(400, 300));

		// Create and set up the content pane.
		JComponent newContentPane = this;
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

}
