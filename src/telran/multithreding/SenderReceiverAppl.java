package telran.multithreding;

import java.util.ArrayList;

import telran.multithreding.consumer.Receiver;
import telran.multithreding.messaging.MessageBox;
import telran.multithreding.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 30;
	private static final int N_RECEIVERS = 20;
	private static final String STOP_MESSAGE = "stop";

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBox = new MessageBox();
		Sender sender = new Sender(messageBox, N_MESSAGES);
		sender.start();
		startReceivers(messageBox);
		sender.join();
		messageBox.put(STOP_MESSAGE);
	}

	private static ArrayList<Receiver> startReceivers(MessageBox messageBox) {
		for(int i = 0; i < N_RECEIVERS; i++) {
			Receiver r = new Receiver(messageBox);
		r.start();
		}
		return null;
		
	}

}
