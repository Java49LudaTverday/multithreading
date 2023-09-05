package telran.threads_race;

public class Racer extends Thread {
	
	RangeSleep rangeSleep = new RangeSleep(2, 5);
	private int nameThread;//number
	private Race race;
	
	
	public Racer( int nameThread, Race race) {
		this.nameThread = nameThread;
		this.race = race;
		
	}
	public int getNameThread() {
		return nameThread;
	}
	
	@Override
	public void run () {
		for(int i = 0; i < race.getDistance(); i++) {
			try {
				sleep(getRandomNumber());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(nameThread);
		}
		race.setResultsRace(true, this);
	}

	private int getRandomNumber() {
		int min = rangeSleep.minValue();
		int max = rangeSleep.maxValue();
		//int randomNum = min + (int)(Math.random() * ((max – min) + 1));
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	

}
