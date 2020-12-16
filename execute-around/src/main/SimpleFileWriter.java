package main;

import java.io.FileWriter;
import java.io.IOException;

public class SimpleFileWriter {

	public SimpleFileWriter(String filename, FileWriterAction action) throws IOException {
	    try (FileWriter writer = new FileWriter(filename)) {
	      action.writeFile(writer);
	    }
	}
}
