package telran.multithresding;

public class FakedDeadlock {

	public static void main(String[] args) throws InterruptedException {
		Thread.currentThread().join();

	}

}
