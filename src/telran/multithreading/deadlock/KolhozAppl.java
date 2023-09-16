package telran.multithreading.deadlock;

import java.awt.MediaTracker;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.IntStream;

public class KolhozAppl {

	private static final int N_TRUCKS_FIRST = 5;
	private static final int N_TRUCKS_SECOND = 5;
	private static final int LOAD = 1;
	private static final int N_LOADS = 10;

	public static void main(String[] args) throws Exception {
		int allTrucks = N_TRUCKS_FIRST + N_TRUCKS_SECOND;
		Kolhoz kolhoz1 = new Kolhoz(1, allTrucks);
		Kolhoz kolhoz2 = new Kolhoz(2, allTrucks);	
		
		Truck[] trucks = new Truck[allTrucks];
		Instant start = Instant.now();
		startTrucks(trucks, kolhoz1, kolhoz2);
		joinTrucks(trucks);
		displayResults(allTrucks, start, kolhoz1, kolhoz2 );
//		System.out.println(kolhoz1.getElevator());
//		System.out.println(kolhoz2.getElevator());

	}

	private static void displayResults(int allTrucks, Instant start, Kolhoz kolhoz1, Kolhoz kolhoz2) {
		System.out.printf("number of trucks is %d; number of loads is %d; "
				+ "one load is %d tons\n"
				+ "elevator1 loaded with %d tons\n"
				+ "elevator2 loaded with %d tons\n"
				+ "running time is %d\n", allTrucks, N_LOADS, LOAD, 
				kolhoz1.getElevator(), kolhoz2.getElevator(), 
				ChronoUnit.MILLIS.between(start, Instant.now()));
		
	}

	private static void joinTrucks(Truck[] trucks) throws Exception {
		for(Truck truck: trucks) {
			truck.join();
		}		
	}

	private static void startTrucks(Truck[] trucks, Kolhoz kolhoz1, Kolhoz kolhoz2) {
		IntStream.range(0, trucks.length).forEach(i -> {
			if(i%2 == 0) {
				trucks[i] = new Truck(LOAD, N_LOADS, kolhoz1, kolhoz2);
			} else {
				trucks[i] = new Truck(LOAD, N_LOADS, kolhoz2, kolhoz1);
			}			
			trucks[i].start();
		});
	}

}
