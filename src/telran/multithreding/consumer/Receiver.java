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
	}
	@Override
	public void run() {
		try {
		while(running) {//FIXME			
				String message = messageBox.get();
				if(message == "stop" ) {
					stopReceiver();
				} else {
					System.out.printf("Thread %d has got message: %s\n", getId(), message);
				}				
		}
			messageBox.put("stop");
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	

}
