package telran.threads_race;

public class Racer extends Thread {
	
	RangeSleep rangeSleep = new RangeSleep(2, 5);
	private long idThread;//number
	private Race race;
	
	
	public Racer( int idThread, Race race) {
		this.idThread = idThread;
		this.race = race;
		
	}
	public long getIdThread() {
		return idThread;
	}
	
	@Override
	public void run () {
		for(int i = 0; i < race.getDistance(); i++) {
			try {
				sleep(getRandomNumber());
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(idThread);
		}
		race.setWinner(idThread);
	}

	private int getRandomNumber() {
		int min = rangeSleep.minValue();
		int max = rangeSleep.maxValue();
		//int randomNum = min + (int)(Math.random() * ((max – min) + 1));
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	

}
