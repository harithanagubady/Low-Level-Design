package com.mylogger;

import com.mylogger.config.LogLevel;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LogEvent {

    String name;
    LogLevel logLevel;
    String message;
}
