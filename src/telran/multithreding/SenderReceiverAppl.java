package telran.multithreding;

import java.util.ArrayList;

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
		ArrayList<Receiver> receivers = startReceivers(messageBox);
		sender.join();
		
		while(messageBox.take() != null) {	
			Thread.sleep(1);			
		}
		System.out.println(messageBox.take());
			stopReceivers(receivers);	
		
	}

	private static void stopReceivers(ArrayList<Receiver> receivers) {
		
//		receivers.stream().forEach(r -> r.stopReceiver());
		for (int i = 0; i < N_RECEIVERS; i++) {
			receivers.get(i).stopReceiver();
			System.out.println("stop");
		}
		
	}

	private static ArrayList<Receiver> startReceivers(MessageBox messageBox) {
		ArrayList<Receiver> receivers = new ArrayList<>();
		for(int i = 0; i < N_RECEIVERS; i++) {
			Receiver r = new Receiver(messageBox);
		receivers.add(r);
		r.start();
		}
		return receivers;
		
	}

}
