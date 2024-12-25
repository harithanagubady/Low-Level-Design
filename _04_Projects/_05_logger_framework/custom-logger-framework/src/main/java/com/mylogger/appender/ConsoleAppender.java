package com.mylogger.appender;

import com.mylogger.LogEvent;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@ToString
public class ConsoleAppender extends Appender {

    private static final AtomicInteger COUNT;

    static {
        COUNT = new AtomicInteger();
    }

    public ConsoleAppender(String appenderName) {
        super(appenderName, AppenderType.CONSOLE);
    }

    public static Appender createDefaultAppender() {
        return new ConsoleAppender("DefaultConsoleAppender-" + COUNT.incrementAndGet());
    }

    @Override
    public void append(LogEvent event) {
        System.out.printf("%s [%s] %s: %s%n", event.getLogLevel(), event.getName(),
                event.getLogLevel(), event.getMessage());
    }
}
