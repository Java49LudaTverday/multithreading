package telran.threads_race;

import java.util.ArrayList;

import telran.console.ConsoleColors;
import telran.view.*;

public class RaceControllerAppl {

	public static void main(String[] args) throws InterruptedException {
		InputOutput io = new ConsoleInputOutput();
		Race race = new Race();
		ThreadsRaceMenu raceMenu = new ThreadsRaceMenu();
		ArrayList<Item> raceItems = raceMenu.getThreadsRaceItem();
		Menu menu = new Menu("Threads race ", raceItems);		
		menu.perform(io);
//		ArrayList<Racer> racers = new ArrayList<>();
//		race.printInformAboutRace();
//		Racer racer1 = new Racer(1, race);
//		Racer racer2 = new Racer(2, race);
//		Racer racer3 = new Racer(3, race);
//		racer1.start();
//		racer2.start();
//		racer3.start();
//		racer1.join();
//		racer2.join();
//		racer3.join();
//		printResult(race.getResults());

	}

	private static ArrayList<Racer> createRacers(int nThreads, Race race) {
		//TODO
		return null;
	}

//	private static void printResult(ArrayList<Racer> winRacer) {
//		
//		System.out.printf("\n%s The RACE is ended.\n    RESULTS:%s \n\n", ConsoleColors.BLACK_BOLD, ConsoleColors.RESET);
//		System.out.printf("%s  First: thread#%d%s\n",ConsoleColors.RED_BOLD_BRIGHT, winRacer.get(0).nThread, ConsoleColors.RESET);
//		System.out.printf("%s  Second: thread#%d%s\n",ConsoleColors.PURPLE, winRacer.get(1).nThread, ConsoleColors.RESET);
//		System.out.printf("%s  Third: thread#%d%s\n\n" ,ConsoleColors.GREEN, winRacer.get(2).nThread, ConsoleColors.RESET);
//		int winner = winRacer.get(0).nThread;
//		System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT  + 
//				" Congratulations to THREAD#" + winner + " " + ConsoleColors.RESET);
//
//	}

}
