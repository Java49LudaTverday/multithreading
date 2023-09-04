package telran.threads_race;

public  class Race {
	private static int distance;
	private static int nThreads;
	private static RangeSleep rangeSleep;
	
	public Race(int distance, int nThreads, RangeSleep rangeSleep) {
		this.distance = distance;
		this.nThreads = nThreads;
		this.rangeSleep= rangeSleep;
	}
	
	public  void printInformAboutRace() {
		System.out.printf("Race with number of THREADS: %d  and distance: %d \n", nThreads, distance );
	}
	public int getDistance() {
		return distance;
	}
	public RangeSleep getRangeSleep() {
		return rangeSleep;
	}

}
