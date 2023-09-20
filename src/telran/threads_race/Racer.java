package telran.threads_race;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Racer extends Thread {
	
	RangeSleep rangeSleep;
	private int idThread;//number
	private static Race race;
	private long finishTime;
	private static Object mutex = new Object();
	
	
	public Racer( int idThread, Race race) {
		this.idThread = idThread;
		this.race = race;
		rangeSleep = race.getRangeSleep();
		
	}
	public int getIdThread() {
		return idThread;
	}
	public long getFinishTime() {		
		return  finishTime;
	}
	
	@Override
	public void run () {
		int min = rangeSleep.minValue();
		int max = rangeSleep.maxValue();
		int range = max - min + 1;
		for(int i = 0; i < race.getDistance(); i++) {
			try {
				sleep(getRandomNumber(min, range));
				
			} catch (InterruptedException e) {
				throw new IllegalStateException();
			}
			System.out.println(idThread);
		}	
		try {
			race.lock.lock();
			finishTime = setFinishTime();
			setResult(this);
		} finally {
			race.lock.unlock();
		}
	}

	private void setResult(Racer racer) {
		race.setResultsRace(racer);		
	}
	
	private static long setFinishTime() {
		
		return ChronoUnit.MILLIS.between(race.getstartTime(), Instant.now());
		
	}
	
	private int getRandomNumber(int min, int range) {		
		//int randomNum = min + (int)(Math.random() * ((max – min) + 1));
		return min + (int)(Math.random() * range);
		}
}
