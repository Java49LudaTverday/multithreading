package telran.multithreding.consumer;

import telran.multithreding.messaging.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;
	private boolean running;

	public Receiver(MessageBox message) {
//		setDaemon(true);//FIXME
		this.messageBox = message;
		running = true;
	}
	public void stopReceiver() {
		running = false;
		System.out.println("false");
	}
	@Override
	public void run() {
		while(running) {//FIXME
			try {
				String message = messageBox.get();
				System.out.printf("Thread %d has got message: %s\n", getId(), message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("true");
		}
	}
	

}
