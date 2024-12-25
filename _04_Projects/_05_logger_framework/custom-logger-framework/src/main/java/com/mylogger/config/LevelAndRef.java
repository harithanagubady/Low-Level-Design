package com.mylogger.config;

import com.mylogger.appender.AppenderRef;
import lombok.Data;

import java.util.List;

@Data
public class LevelAndRef {

    private LogLevel level;
    private List<AppenderRef> refs;
}
