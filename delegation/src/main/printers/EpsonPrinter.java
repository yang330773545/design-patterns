package main.printers;

import main.Printer;

public class EpsonPrinter implements Printer {

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		System.out.println("Epson Printer : " + message);
	}

}
