package telran.multithreding.consumer;

import telran.multithreding.messaging.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;

	public Receiver(MessageBox message) {
		setDaemon(true);//FIXME
		this.messageBox = message;
	}
	@Override
	public void run() {
		while(true) {//FIXME
			try {
				String message = messageBox.get();
				System.out.printf("Thread %d has got message: %s\n", getId(), message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
