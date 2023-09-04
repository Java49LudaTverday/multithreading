package telran.threads_race;

import java.util.ArrayList;

public class RaceControllerAppl {
 public static final int N_THREADS = 3; //{3-10}
 public static final int DISTANCE = 100; // {100-3500}
 
	public static void main(String[] args) {
		RangeSleep rangeSleep = new RangeSleep(2, 5);
		Race race = new Race(DISTANCE, N_THREADS, rangeSleep);
		
		race.printInformAboutRace();
		ArrayList<Racer> racers = createRecers(N_THREADS, race );
		racers.stream().forEach(r -> {
			try {
				r.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		printResult();

	}

	private static ArrayList<Racer> createRecers(int nThreads, Race race) {
		ArrayList<Racer> racers = new ArrayList<>();
		for(int i = 0; i< nThreads; i++) {
			Racer racer = new Racer(i + 1, race);
			racers.add(racer);
			racer.start();
		}
		
		return racers;
	}

	private static void printResult() {
		System.out.println("Race ended");
		
	}

}
