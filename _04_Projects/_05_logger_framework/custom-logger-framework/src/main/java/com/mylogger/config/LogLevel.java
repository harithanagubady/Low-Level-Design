package com.mylogger.config;

import org.apache.commons.lang3.StringUtils;

public enum LogLevel {

    ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF;

    public static LogLevel getLevel(final String level) {
        return level == null ? null : LogLevel.valueOf(StringUtils.toRootUpperCase(level));
    }
}
