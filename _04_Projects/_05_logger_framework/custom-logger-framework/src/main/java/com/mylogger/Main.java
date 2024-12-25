package com.mylogger;

import com.mylogger.config.LogLevel;
import com.mylogger.config.LogManager;
import com.mylogger.log.Logger;

public class Main {
    static Logger log = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.log(LogLevel.ERROR, "checking error log");
        log.log(LogLevel.DEBUG, "checking debug log");
        log.info("checking info log");
    }
}
