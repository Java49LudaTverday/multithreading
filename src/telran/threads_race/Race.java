package telran.threads_race;
import java.util.*;

public  class Race {
	
	private static int distance;
	private static int nThreads;
	private static List<Racer> resultsRace = new ArrayList<>();
	
	public Race(int nThreads, int distance) {
		Race.distance = distance;
		Race.nThreads = nThreads;
		resultsRace.clear();
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
	
	public void setResultsRace(boolean canceled, Racer racer) {
		if(canceled) {
			resultsRace.add(racer);
		}		
	}
	public List<Racer> getResults() {
		return resultsRace;
	}

}
