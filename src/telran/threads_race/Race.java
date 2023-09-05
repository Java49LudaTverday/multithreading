package telran.threads_race;
import java.util.*;

public  class Race {
	
	private static int distance;
	private static int nThreads;
	private static ArrayList<Racer> resultsRace= new ArrayList<>();
	
	public Race() {
		resultsRace.clear();
	}
	
	public void setDistance (int distance) {
		Race.distance = distance;
	}
	
	public void setNThreads (int nThreads) {
		Race.nThreads = nThreads;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public  void printInformAboutRace() {
		System.out.printf("Race with number of THREADS: %d  and distance: %d \n", nThreads, distance );
	}
	
	public void setResultsRace(boolean canceled, Racer racer) {
		if(canceled) {
			resultsRace.add(racer);
		}		
	}
	public ArrayList<Racer> getResults() {
		return resultsRace;
	}

}
