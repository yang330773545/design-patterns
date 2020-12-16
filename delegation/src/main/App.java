package main;

import main.printers.CanonPrinter;
import main.printers.EpsonPrinter;
import main.printers.HpPrinter;

/*
 * 代理模式
 */
public class App {

	private static final String MESSAGE_TO_PRINT = "hello world";
	
	public static void main(String[] args) {
		Printer canonPrinter = new CanonPrinter();
		Printer epsonPrinter = new EpsonPrinter();
		Printer hpPrinter = new HpPrinter();
		
		canonPrinter.print(MESSAGE_TO_PRINT);
		epsonPrinter.print(MESSAGE_TO_PRINT);
		hpPrinter.print(MESSAGE_TO_PRINT);
	}
}
