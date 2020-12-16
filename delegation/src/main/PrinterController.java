package main;

public class PrinterController implements Printer {
	
	private final Printer printer;
	
	public PrinterController(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void print(String message) {
		// TODO Auto-generated method stub
		printer.print(message);
	}

}
