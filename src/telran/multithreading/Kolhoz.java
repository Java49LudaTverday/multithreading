package telran.multithreading;

public class Kolhoz {
	private long id;
	private static long elevator;
	private int nTrucks;
	
	public Kolhoz(long id, int nTrucks) {
		this.nTrucks = nTrucks;
	}

	public  void setElevator(long load) {
		elevator += load;
	}

	public  long getElevator() {
		return elevator;
	}
	
	
	
	
	
	
	

}
