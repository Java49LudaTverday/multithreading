package telran.threads_race;

import java.util.*;

import telran.console.ConsoleColors;
import telran.view.InputOutput;
import telran.view.Item;

public class ThreadsRaceMenu {
	private static final int MIN_DISTANCE = 100;
	private static final int MAX_DISTANCE = 3500;
	private static final int MIN_THREADS = 3;
	private static final int MAX_THREADS = 10;
	

	public ArrayList<Item> getThreadsRaceItem() {
		ArrayList<Item> res = new ArrayList<>(Arrays.asList(getItems()));
		return res;
	}

	private Item[] getItems() {

		return new Item[] { Item.of("Start race: ", io -> getThreadsRaceItem(io)), Item.ofExit() };
	}

	private void getThreadsRaceItem(InputOutput io) {
		int nThreads = io.readInt("Enter number of threads: ", "must be a number ", MIN_THREADS, MAX_THREADS);
		int distance = io.readInt("Enter distance:", "Must be a number", MIN_DISTANCE, MAX_DISTANCE);
		Race race = new Race();
		race.setNThreads(nThreads);
		race.setDistance(distance);

		race.printInformAboutRace();
		Racer racer1 = new Racer(1, race);
		Racer racer2 = new Racer(2, race);
		Racer racer3 = new Racer(3, race);
		racer1.start();
		racer2.start();
		racer3.start();
		try {
			racer1.join();
			racer2.join();
			racer3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		printResult(race.getResults());
	}

	private static void printResult(ArrayList<Racer> winRacer) {

		System.out.printf("\n%s The RACE is ended.\n    RESULTS:%s \n\n", ConsoleColors.BLACK_BOLD,
				ConsoleColors.RESET);
		System.out.printf("%s  First: thread#%d%s\n", ConsoleColors.RED_BOLD_BRIGHT, winRacer.get(0).nThread,
				ConsoleColors.RESET);
		System.out.printf("%s  Second: thread#%d%s\n", ConsoleColors.PURPLE, winRacer.get(1).nThread,
				ConsoleColors.RESET);
		System.out.printf("%s  Third: thread#%d%s\n\n", ConsoleColors.GREEN, winRacer.get(2).nThread,
				ConsoleColors.RESET);
		int winner = winRacer.get(0).nThread;
		System.out.println(
				ConsoleColors.BLUE_BOLD_BRIGHT + " Congratulations to THREAD#" + winner + " " + ConsoleColors.RESET);

	}

}
