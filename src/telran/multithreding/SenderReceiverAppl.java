package telran.multithreding;

import java.util.ArrayList;

import telran.multithreding.consumer.Receiver;
import telran.multithreding.messaging.MessageBox;
import telran.multithreding.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 20;
	

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		startReceivers(messageBox, receivers);
		sender.join();
		stopReceivers(receivers);
	}

	private static void stopReceivers(Receiver[] receivers) {
		for(Receiver r: receivers) {
			r.interrupt();
		}		
	}

	private static Receiver[] startReceivers(MessageBox messageBox, Receiver[] receivers) {
		for(int i = 0; i < receivers.length; i++) {
			receivers [i] = new Receiver(messageBox);
			receivers [i].start();
		}
		return receivers;
		
	}

}
