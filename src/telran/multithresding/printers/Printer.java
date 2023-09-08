package telran.multithresding.printers;

public class Printer extends Thread {
	private int nNumbers;
	private int nPortions;	
	private int printerId;
	Printer nextPrinter;
	private int running = 0;
	
	public Printer(int id, int nNumbers, int nPortions) {
		this.nNumbers =  nNumbers;
		this.nPortions = nPortions;
		this.printerId = id;
		
	}
	public void setNextPrinter (Printer next) {
		this.nextPrinter = next;
	}
	
	public void stopPrinter() {
		running = nNumbers;
	}
	
	@Override
	public void run () {	
		while(running < nNumbers) {
			try {
				join();
			} catch (InterruptedException e) {	
				printPortions(nPortions, printerId);			
				running += nPortions;
				nextPrinter.interrupt();				
			}
		}
	}
	
	private void printPortions(int nPortions, int data) {
		for(int i = 0; i < nPortions; i++) {
			System.out.print(data);
		}
		System.out.println();
	}
	
	

}
