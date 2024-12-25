package com.mylogger.appender;

import com.mylogger.LogEvent;
import lombok.ToString;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@ToString
public class FileAppender extends Appender {

    private final String fileName;

    public FileAppender(String name, String fileName) {
        super(name, AppenderType.FILE);
        this.fileName = fileName;
    }


    @Override
    public void append(LogEvent event) {
        System.out.println("Writing to file...");
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            String msg = String.format("%s [%s] %s: %s%n", event.getLogLevel(), event.getName(),
                    event.getLogLevel(), event.getMessage());
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
