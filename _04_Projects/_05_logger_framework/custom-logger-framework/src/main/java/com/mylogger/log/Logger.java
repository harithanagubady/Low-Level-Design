package com.mylogger.log;

import com.mylogger.appender.Appender;
import com.mylogger.appender.AppenderRef;
import com.mylogger.config.LogLevel;
import com.mylogger.config.LoggerConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class Logger {

    private String name;
    private LoggerConfig config;

    public void log(LogLevel level, String message) {
        this.config.log(level, message);
    }

    public void error(String message) {
        this.log(LogLevel.ERROR, message);
    }

    public void debug(String message) {
        this.log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        this.log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        this.log(LogLevel.WARN, message);
    }

    public void setAppenderRefs(List<AppenderRef> refs) {
        this.config.addAppenderRefs(refs);
    }

    public void addAppender(Appender appender) {
        this.config.addAppender(appender);
    }

    public void setAdditivity(boolean additivity) {
        this.config.setAdditivity(additivity);
    }
}
