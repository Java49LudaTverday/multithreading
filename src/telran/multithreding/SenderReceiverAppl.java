package telran.multithreding;

import java.util.ArrayList;

import telran.multithreding.consumer.Receiver;
import telran.multithreding.messaging.MessageBox;
import telran.multithreding.producer.Sender;

public class SenderReceiverAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox messageBoxOdd = new MessageBox();
		MessageBox messageBoxEven = new MessageBox();
		Sender sender = new Sender(messageBoxOdd, messageBoxEven, N_MESSAGES);
		sender.start();
		startReceivers(messageBoxOdd, messageBoxEven);
		Thread.sleep(300);
	}

	private static void startReceivers(MessageBox messageBoxOdd, MessageBox messageBoxEven) {
		for(int i = 0; i < N_RECEIVERS; i++) {
			Receiver r = new Receiver(messageBoxEven);
		    long id = r.getId();
			if(id % 2 != 0) {
				r.start();			
			} else {
		 r .setMessageBox(messageBoxOdd);		
			  r.start();
			}
		}
	}

}
