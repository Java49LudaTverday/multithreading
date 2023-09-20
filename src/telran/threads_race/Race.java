package telran.threads_race;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.locks.*;

public  class Race {
	
	private static int distance;
	private static int nThreads;
	private static RangeSleep rangeSleep;
	private static List<Racer> resultsRace = new LinkedList<>();
	final Lock lock = new ReentrantLock(true);
	private static Instant startTime;
	
	public Race(int nThreads, int distance, int minSleep, int maxSleep) {
		resultsRace.clear();
		Race.distance = distance;
		Race.nThreads = nThreads;
		rangeSleep = new RangeSleep(minSleep, maxSleep);
		startTime = Instant.now();
	}
	
	public int getDistance() {
		return distance;
	}
	public int getNumberTreads() {
		return nThreads;
	}
	public RangeSleep getRangeSleep() {
		return rangeSleep;
	}
	public Instant getstartTime() {
		return startTime;
	}
	
	public  void printInformAboutRace() {
		System.out.printf("Race with number of THREADS: %d  and distance: %d \n", nThreads, distance );
	}
	
	public  void setResultsRace(Racer racer) {		
			resultsRace.add(racer);	
	}
	public List<Racer> getResults() {
		return resultsRace;
	}

}
