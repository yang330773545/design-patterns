package main;

import java.io.IOException;

// “执行环绕” 使用户摆脱某些应始终在业务方法之前和之后执行的操作。
public class App {

	public static void main(String[] args) throws IOException {

		FileWriterAction writeHello = writer -> {
		    writer.write("Hello");
		    writer.append(" ");
		    writer.append("there!");
		};
		new SimpleFileWriter("testfile.txt", writeHello);
	}
}
