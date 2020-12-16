package main.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import main.event.AccountCreateEvent;
import main.event.DomainEvent;
import main.event.MoneyDepositEvent;
import main.event.MoneyTransferEvent;

/**
 * 事件日志
 * 使用JSON，并在工作目录的Journal.json文件中写入/读取它们。
 *
 */
public class JsonFileJournal {
	private final File file;
	private final List<String> events = new ArrayList<>();
	private int index = 0;

	public JsonFileJournal() {
	    file = new File("Journal.json");
	    if (file.exists()) {
	        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
	        	String line;
	        	while ((line = input.readLine()) != null) {
	        		events.add(line);
	        	}
	        } catch (IOException e) {
	        	throw new RuntimeException(e);
	        }
	    } else {
	    	reset();
	    }
	}
	
	public void write(DomainEvent domainEvent) {
		Gson gson = new Gson();
	    JsonElement jsonElement;
	    if (domainEvent instanceof AccountCreateEvent) {
	        jsonElement = gson.toJsonTree(domainEvent, AccountCreateEvent.class);
	    } else if (domainEvent instanceof MoneyDepositEvent) {
	        jsonElement = gson.toJsonTree(domainEvent, MoneyDepositEvent.class);
	    } else if (domainEvent instanceof MoneyTransferEvent) {
	        jsonElement = gson.toJsonTree(domainEvent, MoneyTransferEvent.class);
	    } else {
	        throw new RuntimeException("Journal Event not recegnized");
	    }

	    try (BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
	        String eventString = jsonElement.toString();
	        output.write(eventString + "\r\n");
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public void reset() {
		file.delete();
	}
	
	public DomainEvent readNext() {
	    if (index >= events.size()) {
	        return null;
	    }
	    String event = events.get(index);
	    index++;

	    JsonParser parser = new JsonParser();
	    JsonElement jsonElement = parser.parse(event);
	    String eventClassName = jsonElement.getAsJsonObject().get("eventClassName").getAsString();
	    Gson gson = new Gson();
	    DomainEvent domainEvent;
	    if (eventClassName.equals("AccountCreateEvent")) {
	        domainEvent = gson.fromJson(jsonElement, AccountCreateEvent.class);
	    } else if (eventClassName.equals("MoneyDepositEvent")) {
	        domainEvent = gson.fromJson(jsonElement, MoneyDepositEvent.class);
	    } else if (eventClassName.equals("MoneyTransferEvent")) {
	        domainEvent = gson.fromJson(jsonElement, MoneyTransferEvent.class);
	    } else {
	        throw new RuntimeException("Journal Event not recegnized");
	    }

	    domainEvent.setRealTime(false);
	    return domainEvent;
	}
}
