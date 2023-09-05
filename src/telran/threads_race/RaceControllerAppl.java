package telran.threads_race;

import java.util.ArrayList;
import telran.view.*;

public class RaceControllerAppl {

	public static void main(String[] args) {
		
		InputOutput io = new ConsoleInputOutput();
		ThreadsRaceMenu raceMenu = new ThreadsRaceMenu();
		ArrayList<Item> raceItems = raceMenu.getThreadsRaceItem();
		Menu menu = new Menu("Threads race ", raceItems);		
		menu.perform(io);
	}


}
