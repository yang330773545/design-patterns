package main;

import java.io.Closeable;
import java.io.IOException;

public class TreasureChest implements Closeable {
	
	public TreasureChest() {
		System.out.println("Treasure chest opens.");
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Treasure chest closes.");
	}

}
