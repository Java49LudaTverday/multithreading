package telran.threads_race;
import java.util.*;

public  class Race {
	
	private static int distance;
	private static int nThreads;
	private int winner = -1;
	
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
	
	public void setWinner(int winner) {
		if (this.winner == -1) {
			this.winner = winner;
		}
	}
	public int getResults() {
		return winner;
	}

}
