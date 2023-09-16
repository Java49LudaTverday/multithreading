package telran.multithreading.deadlock;

import java.util.concurrent.atomic.AtomicLong;

public class Truck extends Thread {
	private static final long BREAKTIME = 100;
	private int load;
	private Kolhoz kolhozFirst;
	private Kolhoz kolhozSecond;
	private int nLoads;

	public Truck(int load, int nLoads, Kolhoz first, Kolhoz second) {
		this.load = load;
		this.nLoads = nLoads;
		this.kolhozFirst = first;
		this.kolhozSecond = second;
	}

	@Override
	public void run() {
		for (int i = 0; i < nLoads; i++) {
			synchronized (kolhozFirst) {				
				kolhozFirst.setElevator(load);
				//first way: example
//				System.out.println(kolhozFirst.getElevator());
				
				//second way: get break for trucker
				try {
					sleep(BREAKTIME);
				} catch (InterruptedException e) {

				}
				synchronized (kolhozSecond) {
					kolhozSecond.setElevator(load);
//					System.out.println(kolhozSecond.getElevator());
				}
			}
		}
	}

}
