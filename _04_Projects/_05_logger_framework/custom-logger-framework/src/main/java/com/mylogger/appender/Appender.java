package com.mylogger.appender;

import com.mylogger.LogEvent;

@SuppressWarnings("all")
public abstract class Appender {

    private final String name;
    private final AppenderType type;

    public Appender (String name, AppenderType appenderType) {
        this.name = name;
        this.type = appenderType;
    }

    public abstract void append(LogEvent event);

    public String getName() {
        return name;
    }
}
