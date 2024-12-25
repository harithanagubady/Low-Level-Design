package com.mylogger.config;

import com.mylogger.LogEvent;
import com.mylogger.appender.Appender;
import com.mylogger.appender.AppenderRef;
import com.mylogger.log.Logger;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class LoggerConfig {

    private String name;
    private LogLevel level;
    private Logger logger;
    private LoggerConfig parent;
    private List<AppenderRef> appenderRefs = new ArrayList<>();
    private Map<String, Appender> appenders = new HashMap<>();
    private boolean additivity = true;

    public LoggerConfig() {
        this("", LogLevel.ERROR);
    }

    public LoggerConfig(String name, LogLevel level) {
        this(name, level, null, null, null);
    }

    public LoggerConfig(String name, LogLevel level, LoggerConfig parent, List<AppenderRef> refs) {
        this(name, level, parent, null, refs);
    }

    public LoggerConfig(String name, LogLevel level, LoggerConfig parent, Map<String, Appender> appenders, List<AppenderRef> refs) {
        this.name = name;
        this.level = level;
        this.logger = new Logger(name, this);
        addAppenderRefs(refs);
        addAppenders(appenders);
        this.parent = parent;
    }

    public LoggerConfig(String name, LogLevel level, Map<String, Appender> appenderMap) {
        this(name, level, null, appenderMap, null);
    }

    public LogLevel getLevel() {
        return this.level == null ? (this.parent == null ? LogLevel.ERROR : this.parent.getLevel()) : this.level;
    }

    public void setAppenders(Map<String, Appender> appenders) {
        if (appenders != null) this.appenders = appenders;
    }

    @Override
    public String toString() {
        return "LoggerConfig{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", parent=" + parent +
                ", appenderRefs=" + appenderRefs +
                ", appenders=" + appenders +
                ", additivity=" + additivity +
                '}';
    }

    public void addAppenders(Map<String, Appender> appenders) {
        if (appenders != null) this.appenders.putAll(appenders);
    }

    public void addAppender(Appender appender) {
        if (appender != null) {
            this.appenders.putIfAbsent(appender.getName(), appender);
        }
    }

    public void addAppenderRefs(List<AppenderRef> refs) {
        if (refs != null) this.appenderRefs.addAll(refs);
    }

    public void log(LogLevel level, String message) {
        LogEvent logEvent = new LogEvent(this.name, level, message);
        this.log(logEvent);
    }

    private void log(LogEvent logEvent) {
        processLogEvent(logEvent);
        this.logParent(logEvent);
    }

    private void processLogEvent(LogEvent logEvent) {
        if (logEvent.getLogLevel().ordinal() >= this.getLevel().ordinal()) {
            for (Appender appender : appenders.values()) {
                appender.append(logEvent);
            }
        }
    }

    private void logParent(LogEvent logEvent) {
        if (this.additivity && this.parent != null) this.parent.log(logEvent);
    }

}
