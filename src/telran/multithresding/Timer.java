package telran.multithresding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
	
	private static final DateTimeFormatter DATE_FORMATTER_DEFAULT = 
			       DateTimeFormatter.ofPattern("h:mm:ss a");
	private static final long TIMEOUT_DEFAULT = 1000;
	
	private DateTimeFormatter dtf;
	private long timeout;

	public Timer(DateTimeFormatter dtf, long timeout) {
		this.dtf = dtf;
		this.timeout = timeout;
		setDaemon(true);
	}
	public Timer() {
		this(DATE_FORMATTER_DEFAULT, TIMEOUT_DEFAULT);
	}
	
	@Override
	public void run() {
		boolean running = true;
		while(running) {
			System.out.println(LocalDate.now().format(dtf));
			try {
				sleep(timeout);
			} catch (InterruptedException e) {
				running = false;
			}
		}
	}

}
