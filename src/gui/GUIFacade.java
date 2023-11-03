package gui;

import item.ItemBuffer;
import logg.Logg;
import worker.Worker;
import worker.Workforce;

public class GUIFacade {
	
	ItemBuffer buffer = null;
	Workforce workforce;
	Logg logg;
	String path;
	
	
	public GUIFacade(ItemBuffer buffer, Workforce workforce, Logg logg, String path) {
		super();
		this.buffer = buffer;
		this.workforce = workforce;
		this.logg =logg;
		this.path  = path;
	}

	
	
	

	public void addWorker() {
		
		Worker worker= new Worker(buffer);
		Thread t =  new Thread(worker);
		t.start();
		workforce.addWorker(worker);
		logg.writeData("Added worker that produces 1  unit per " + worker.getInterval() +" seconds. Number of workers: "  + workforce.getLength(), path);
		
	}

	public void removeWorker() {
		if(workforce.getLength()>0) {
		Worker worker = workforce.removeWorker();
		worker.stop();
		logg.writeData("Fired worker that produced 1  unit per " + worker.getInterval() +" seconds. Number of workers: "  + workforce.getLength(), path);
		
		}
	}
	
	public boolean checkItemStockLow() {
		if((double)buffer.getSize()/buffer.getMaxSize()<0.1) {
			return true;
		}
		return false;
	}
	public boolean checkItemStockHigh() {
		if((double)buffer.getSize()/buffer.getMaxSize()>0.9) {
			return true;
		}
		return false;
	}
	
	public int percentInStock() {
		return (int)(((double)buffer.getSize()/buffer.getMaxSize())*100);
	}





	public int getItemBufferSize() {
		// TODO Auto-generated method stub
		return buffer.getSize();
	}
	
	public int getItemBufferMaxSize() {
		return buffer.getMaxSize();
	}





	public String getLogg() {
		// TODO Auto-generated method stub
		return logg.readData(path);
	}

}
