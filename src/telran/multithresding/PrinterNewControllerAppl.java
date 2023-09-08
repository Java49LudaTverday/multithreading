package telran.multithresding;

import java.util.Scanner;

public class PrinterNewControllerAppl {

	public static void main(String[] args) {
		Thread.currentThread().interrupt();
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			
			System.out.println("interrupted");
		}
		
		PrinterNew printer = new PrinterNew(".*#$%&");
		Scanner scanner = new Scanner(System.in);
		printer.start();
		while(true) {
			String line = scanner.nextLine();
			if(line.equals("q")) {
				break;
			}
			printer.interrupt();
		}
		printer.stopPrinter();
		

	}

}
