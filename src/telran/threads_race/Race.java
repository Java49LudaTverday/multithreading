package telran.threads_race;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public  class Race {
	
	private static int distance;
	private static int nThreads;
	private AtomicLong winnerId = new AtomicLong(-1);
	
	public Race(int nThreads, int distance) {
		Race.distance = distance;
		Race.nThreads = nThreads;
	}
	
	public int getDistance() {
		return distance;
	}
	public int getNumberTreads() {
		return nThreads;
	}
	
	public  void printInformAboutRace() {
		System.out.printf("Race with number of THREADS: %d  and distance: %d \n", nThreads, distance );
	}
	
	public void setWinner(long winner) {
		winnerId.compareAndSet(-1, winner);
	}
	public long getResults() {
		return winnerId.get();
	}

}
