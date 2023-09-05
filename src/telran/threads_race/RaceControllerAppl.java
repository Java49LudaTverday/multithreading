package telran.threads_race;

import java.util.ArrayList;

import telran.console.ConsoleColors;

public class RaceControllerAppl {
	public static final int N_THREADS = 3; // {3-10}
	public static final int DISTANCE = 100; // {100-3500}

	public static void main(String[] args) throws InterruptedException {
		RangeSleep rangeSleep = new RangeSleep(2, 5);
		Race race = new Race(DISTANCE, N_THREADS, rangeSleep);
		ArrayList<Racer> racers = new ArrayList<>();
		race.printInformAboutRace();
		Racer racer1 = new Racer(1, race);
		Racer racer2 = new Racer(2, race);
		Racer racer3 = new Racer(3, race);
		racer1.start();
		racer2.start();
		racer3.start();
		racer1.join();
		racer2.join();
		racer3.join();
		printResult(race.getResults());

	}

	private static ArrayList<Racer> createRacers(int nThreads, Race race) {
		//TODO
		return null;
	}

	private static void printResult(ArrayList<Racer> winRacer) {
		
		System.out.printf("\n%s The RACE is ended.\n    RESULTS:%s \n\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
		System.out.printf("%s  First: thread#%d%s\n",ConsoleColors.RED_BOLD_BRIGHT, winRacer.get(0).nThread, ConsoleColors.RESET);
		System.out.printf("%s  Second: thread#%d%s\n",ConsoleColors.PURPLE, winRacer.get(1).nThread, ConsoleColors.RESET);
		System.out.printf("%s  Third: thread#%d%s\n\n" ,ConsoleColors.GREEN, winRacer.get(2).nThread, ConsoleColors.RESET);
		int winner = winRacer.get(0).nThread;
		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT  + 
				" Congratulations to THREAD#" + winner + " " + ConsoleColors.RESET);

	}

}
