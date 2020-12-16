package main.printers;

import main.Printer;

public class HpPrinter implements Printer {

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		System.out.println("Hp Printer : " + message);
	}

}
