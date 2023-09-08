package telran.multithresding.printers;

import java.util.*;

public class PrintersControllerAppl {

	public static final int N_PRINTERS = 5;
	public static final int N_NUMBERS = 50;
	public static final int N_PORTIONS = 3;

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		if ( N_NUMBERS % N_PORTIONS != 0) {
			System.out.println("Wrong data. N_NUMBERS must be a multiple of N_PORTIONS.");
		} else {
			List<Printer> printers = createPrinters(N_PRINTERS);
			startPrinters(printers);
			Printer first = printers.get(0);
			first.interrupt();
		}

	}

	private static void startPrinters(List<Printer> printers) {
		printers.stream().forEach(p -> p.start());

	}

	private static List<Printer> createPrinters(int nPrinters) {
		List<Printer> printers = new ArrayList<>();
		for (int i = 0; i < nPrinters; i++) {
			printers.add(new Printer(i + 1, N_NUMBERS, N_PORTIONS));
		}
		setNextPrinter(printers);
		return printers;
	}

	private static void setNextPrinter(List<Printer> printers) {
		int size = printers.size();
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				printers.get(i).setNextPrinter(printers.get(0));
			} else {
				printers.get(i).setNextPrinter(printers.get(i + 1));
			}
		}
	}

}
