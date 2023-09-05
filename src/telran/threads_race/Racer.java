package telran.threads_race;

public class Racer extends Thread{
	
	private Thread thread;
	public int nThread;
	private Race race;
	
	
	public Racer( int nThread, Race race) {
		thread = new Thread();
		this.nThread = nThread;
		this.race = race;
		
	}
	
	@Override
	public void run () {
		for(int i = 0; i < race.getDistance(); i++) {
			try {
				sleep(getRandomNumber());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(nThread);
		}
		race.setResultsRace(true, this);
	}

	private int getRandomNumber() {
		RangeSleep rangeSleep = race.getRangeSleep();
		int min = rangeSleep.minValue();
		int max = rangeSleep.maxValue();
		//int randomNum = min + (int)(Math.random() * ((max – min) + 1));
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	

}
