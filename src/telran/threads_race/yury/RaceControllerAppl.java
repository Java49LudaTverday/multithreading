package telran.threads_race.yury;
import java.util.stream.IntStream;

import telran.view.*;
public class RaceControllerAppl {
	
	private static final int MAX_THREADS = 10;
	private static final int MIN_THREADS = 3;
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_SLEEP = 2;
	private static final int MAX_SLEEP = 5;
	
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = getItems();
		Menu menu = new Menu("Race Game", items);
		menu.perform(io);

	}

	private static Item[] getItems() {
		Item[] res = {
				Item.of("Start new game", RaceControllerAppl::startGame),
				Item.ofExit()
		};
		return res;
	}
	static void startGame(InputOutput io) {
		int nThreads = io.readInt("Enter number of the runners","Wrong number of the runners",
				MIN_THREADS, MAX_THREADS);
		int distance = io.readInt("Enter distance", "Wrong Distance",MIN_DISTANCE, MAX_DISTANCE);
		RaceYury race = new RaceYury(distance, MIN_SLEEP, MAX_SLEEP);
		RacerYury[] runners = new RacerYury[nThreads];
		startRunners(runners, race);
		joinRunners(runners);
		displayWinner(race);
	}

	private static void displayWinner(RaceYury race) {
		System.out.println("Congratulations to runner " + race.getWinner());
		
	}

	private static void joinRunners(RacerYury[] runners) {
		IntStream.range(0, runners.length).forEach(i -> {
			try {
				runners[i].join();
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
		});
		
	}

	private static void startRunners(RacerYury[] runners, RaceYury race) {
		IntStream.range(0, runners.length).forEach(i -> {
			runners[i] = new RacerYury(race, i + 1);
			runners[i].start();
		});
		
	}
}
