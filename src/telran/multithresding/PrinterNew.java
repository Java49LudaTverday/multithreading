package telran.multithresding;

public class PrinterNew extends Thread {
	private char[] symbols;
	private boolean running = true;	

	public PrinterNew(String symbols) {
		
		this.symbols = symbols.toCharArray();
	}
	public void stopPrinter() {
		this.running = false;
	}
	@Override
	public void run() {
		int index = 0;
		while(running) {
			System.out.print(symbols[index]);
			try {
				sleep(1000);
			} catch (InterruptedException e) {				
				index++;
				if(index == symbols.length) {
					index = 0;
				}
			}
		}
	}

}
