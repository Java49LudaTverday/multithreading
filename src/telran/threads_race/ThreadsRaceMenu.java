package telran.threads_race;

import java.util.*;
import java.util.stream.IntStream;

import telran.console.ConsoleColors;
import telran.view.InputOutput;
import telran.view.Item;


public class ThreadsRaceMenu  {
	private static final int MIN_DISTANCE = 10;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_THREADS = 3;
	private static final int MAX_THREADS = 100_000;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;

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
		
		Race race = new Race(nThreads, distance , MIN_SLEEP, MAX_SLEEP);
		startRace(race);
	}

	private void startRace(Race race) {
		printIntroduction(race);
		List<Racer> racers = createRacers(race);
		setJoinForRacer(racers);
		printResult(race.getResults());
	}

	private List<Racer> createRacers(Race race) {
		List<Racer> racers = new ArrayList<>();
		IntStream.range(0, race.getNumberTreads()).forEach(i -> {
			Racer racer = new Racer(i + 1, race);
			racer.start();
			racers.add(racer);
		});
		return racers;
	}
	
	private void setJoinForRacer(List<Racer> racers) {
		racers.stream().forEach(r -> {
			try {
				r.join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});		
	}	

	private void printIntroduction(Race race) {
		System.out.printf("%s\nThe RACE is STARTED %s\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
		race.printInformAboutRace();
	}

	private static void printResult(List<Racer> resultRace) {		
		printTitle();
		printResultTable(resultRace);		

	}

	private static void printTitle() {
		System.out.printf("\n%s The RACE is ended.\n    RESULTS:%s \n\n", ConsoleColors.BLACK_BOLD,
				ConsoleColors.RESET);
		System.out.printf("%s Place  RacerID  Time%s\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
		
	}

	private static void printResultTable(List<Racer> resultRace) {
//		int place = 1;
//		for(Racer racer: resultRace) {
//			System.out.printf(" %d%s   %d%s   %d\n", place++ , " ".repeat(4),  
//					racer.getIdThread(), " ".repeat(4),  racer.getFinishTime());
//		}
//		System.out.println();
		int size= resultRace.size();
		int count = 0;
		for(int i = 1; i < size; i++) {
			if(resultRace.get(i-1).getFinishTime() > resultRace.get(i).getFinishTime()) {
				count++;
			}
		}
		System.out.printf("Count of wrong places: %d", count);
	}

}
