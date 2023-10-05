package telran.multithreding.producer;

import telran.multithreding.messaging.*;

public class Sender extends Thread {
	private MessageBox messageBoxOdd;
	private MessageBox messageBoxEven;
	private int nMessages;
	
	public Sender(MessageBox messageBoxOdd, MessageBox messageBoxEvent, int nMessages) {
		this.messageBoxOdd = messageBoxOdd;
		this.messageBoxEven = messageBoxEvent;
		this.nMessages = nMessages;
	}
	@Override 
	public void run() {
		for(int i = 1; i <= nMessages; i++) {
			if(i%2 == 0) {
				try {
				this.messageBoxEven.put("message" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			} else {
				try {
					this.messageBoxOdd.put("message" + i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
