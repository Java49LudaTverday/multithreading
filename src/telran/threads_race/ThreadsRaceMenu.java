package telran.threads_race;

import java.util.*;

import telran.console.ConsoleColors;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

public class ThreadsRaceMenu  {
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_THREADS = 3;
	private static final int MAX_THREADS = 10;
	

	public ArrayList<Item> getThreadsRaceItem() {
		ArrayList<Item> res = new ArrayList<>(Arrays.asList(getItems()));
		return res;
	}

	private Item[] getItems() {

		return new Item[] { Item.of(" Start race. ", io -> getThreadsRaceItem(io)),
				            Item.ofExit() };
	}

	private void getThreadsRaceItem(InputOutput io) {
		int nThreads = io.readInt("Enter number of threads: ", "must be a number ", MIN_THREADS, MAX_THREADS);
		int distance = io.readInt("Enter distance:", "Must be a number", MIN_DISTANCE, MAX_DISTANCE);
		
		Race race = new Race(nThreads, distance);
		startRace(race);
	}

	private void startRace(Race race) {
		printIntroduction(race);
		List<Racer> racers = createRacers(race);
		setJoinForRacer(racers);
		printResult(race);
	}

	private List<Racer> createRacers(Race race) {
		List<Racer> racers = new ArrayList<>();
		for (int i = 0; i < race.getNumberTreads(); i++) {
			Racer racer = new Racer(i + 1, race);
			racer.start();
			racers.add(racer);
		}
		return racers;
	}
	
	private void setJoinForRacer(List<Racer> racers) {
		racers.stream().forEach(r -> {
			try {
				r.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});		
	}	

	private void printIntroduction(Race race) {
		System.out.printf("%s\nThe RACE is STARTED %s\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
		
	}

	private static void printResult(Race race) {
          race.printInformAboutRace();
		System.out.printf("\n%s The RACE is ended.\n", ConsoleColors.BLACK_BOLD,
				ConsoleColors.RESET);
		printCongratulation(race.getResults());

	}

	private static void printCongratulation(long firstPlace) {
		System.out.printf("%s Congratulations to THREAD#%d! %s\n\n",ConsoleColors.BLUE_BOLD_BRIGHT
				 ,firstPlace,  ConsoleColors.RESET);
	}


}
