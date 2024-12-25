package com.mylogger.appender;

import lombok.Data;

@Data
public class AppenderRef {

    private String ref;
    public AppenderRef(String ref) {
        this.ref = ref;
    }
}
