package main;

import java.io.FileWriter;
import java.io.IOException;

// 函数式接口只定义了唯一的抽象方法的接口
@FunctionalInterface
public interface FileWriterAction {
	void writeFile(FileWriter writer) throws IOException;
}
