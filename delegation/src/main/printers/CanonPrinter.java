package main.printers;

import main.Printer;

public class CanonPrinter implements Printer {

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		System.out.println("Canon Printer : " + message);
	}

}
