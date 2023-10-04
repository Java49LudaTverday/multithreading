package telran.multithreding;

import telran.multithreding.consumer.Receiver;
import telran.multithreding.messaging.MessageBox;
import telran.multithreding.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		startReceivers(messageBox);
		Thread.sleep(300);
	}

	private static void startReceivers(MessageBox messageBox) {
		for(int i = 0; i < N_RECEIVERS; i++) {
			new Receiver(messageBox).start();
		}
		
	}

}
