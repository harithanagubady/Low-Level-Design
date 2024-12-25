package com.mylogger.appender;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public enum AppenderType {

    CONSOLE("Console"),
    FILE("File");

    @Getter private final String type;

    AppenderType(String type) {
        this.type = type;
    }

    public static AppenderType getAppender (String type) {
        for (AppenderType t : AppenderType.values()) {
            if (StringUtils.equalsIgnoreCase(type, t.getType())) {
                return t;
            }
        }
        return null;
    }
}
