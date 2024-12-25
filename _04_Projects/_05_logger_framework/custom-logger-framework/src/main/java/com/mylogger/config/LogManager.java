package com.mylogger.config;

import com.mylogger.appender.*;
import com.mylogger.log.Logger;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class LogManager {

    @Getter
    @Setter
    protected static LoggerConfig rootLoggerConfig;
    private static LogLevel defaultLevel;
    private static Map<String, LoggerConfig> loggerConfigMap;
    @Getter
    @Setter
    private static Map<String, Appender> appenderMap;

    static {
        setDefault();
        String propertiesFilePath = "mylog.properties";
        PropertiesConfigurationLoader configLoader = new PropertiesConfigurationLoader(propertiesFilePath);
        configLoader.loadConfigurations();
        setAppendersToLoggers();
        setParents();
    }

    private static void setParents() {

        for (Map.Entry<String, LoggerConfig> entry : loggerConfigMap.entrySet()) {
            LoggerConfig logger =  entry.getValue();
            String key = entry.getKey();
            if (!key.isEmpty()) {
                int i = key.lastIndexOf('.');
                if (i > 0) {
                    key = key.substring(0, i);
                    LoggerConfig parent = getLoggerConfig(key);
                    if (parent == null) {
                        parent = getRootLoggerConfig();
                    }

                    logger.setParent(parent);
                } else {
                    logger.setParent(getRootLoggerConfig());
                }
            }
        }

    }
    private static void setAppendersToLoggers() {

        for (Map.Entry<String, LoggerConfig> e : loggerConfigMap.entrySet()) {

            LoggerConfig loggerConfig = e.getValue();
            for (AppenderRef ref : loggerConfig.getAppenderRefs()) {
                Appender app = appenderMap.get(ref.getRef());
                if (app != null) {
                    loggerConfig.addAppender(app);
                } else {
                    String msg = String.format("Unable to locate appender \"%s\" for logger config \"%s\"", ref.getRef(), loggerConfig);
                    throw new RuntimeException(msg);
                }
            }
        }
        setRootLoggerConfig(loggerConfigMap.get(""));
    }

    private static void setDefault() {
        loggerConfigMap = new HashMap<>();
        appenderMap = new HashMap<>();
        setDefaultLevel(LogLevel.ERROR);
        Appender appender = addDefaultAppender();
        Map<String, Appender> map = new HashMap<>();
        map.put(appender.getName(), appender);
        setRootLoggerConfig(new LoggerConfig("", getDefaultLevel(), map));
        addRootLoggerConfig(rootLoggerConfig);
    }

    private static Appender addDefaultAppender() {
        Appender appender = getDefaultAppender();
        appenderMap.put(appender.getName(), appender);
        return appender;
    }

    public static Appender getDefaultAppender() {
        return ConsoleAppender.createDefaultAppender();
    }

    private static void addAppender(Appender appender) {
        if (appender != null) {
            appenderMap.put(appender.getName(), appender);
        }
    }

    public static void setDefaultLevel(LogLevel level) {
        defaultLevel = level;
    }

    public static LogLevel getDefaultLevel() {
        return defaultLevel;
    }

    public static Logger getLogger(String loggerName) {
        LoggerConfig loggerConfig = loggerConfigMap.get(loggerName);
        if (loggerConfig != null) return loggerConfig.getLogger();
        String parentName = loggerName;
        do {
            if ((parentName = getSubName(parentName)) == null) {
                return createLogger(loggerName, null, getRootLoggerConfig());
            }
            loggerConfig = getLoggerConfig(parentName);
        } while (loggerConfig == null);

        return createLogger(loggerName, null, loggerConfig);
    }

    private static LoggerConfig getLoggerConfig(String name) {
        return loggerConfigMap.get(name);
    }

    private static String getSubName(final String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        } else {
            int i = name.lastIndexOf(46);
            return i > 0 ? name.substring(0, i) : "";
        }
    }

    @SuppressWarnings("all")
    protected static Logger createLogger(String loggerName, LogLevel logLevel) {
        return createLogger(loggerName, logLevel, (LoggerConfig) null, new ArrayList<>());
    }

    @SuppressWarnings("all")
    private static Logger createLogger(String loggerName, LogLevel logLevel, LoggerConfig loggerConfig) {
        return createLogger(loggerName, logLevel, loggerConfig, null);
    }

    public static void createRootLogger(LogLevel rootLevel, List<AppenderRef> refs) {
        setDefaultLevel(rootLevel);
        Logger rootLogger = createLogger("", getDefaultLevel(), null, refs);
        rootLogger.addAppender(getDefaultAppender());
        addRootLoggerConfig(rootLogger.getConfig());
    }


    public static void createLogger(String loggerName, LogLevel logLevel, List<AppenderRef> appenderRefs, boolean additivity) {
        Logger logger = createLogger(loggerName, logLevel, null, appenderRefs);
        logger.setAdditivity(additivity);
        addLogger(loggerName, logger.getConfig());
    }

    protected static Logger createLogger(String loggerName, LogLevel logLevel, LoggerConfig parent,
                                         List<AppenderRef> refs) {
        LoggerConfig loggerConfig = new LoggerConfig(loggerName, logLevel, parent, refs);
        Logger logger = new Logger(loggerName, loggerConfig);
        loggerConfig.setLogger(logger);
        addLogger(loggerName, loggerConfig);
        return logger;
    }

    public static void addLogger(String loggerName, LoggerConfig loggerConfig) {
        loggerConfigMap.put(loggerName, loggerConfig);
    }

    private static void addRootLoggerConfig(LoggerConfig rootConfig) {
        addLogger(rootConfig.getName(), rootConfig);
    }

    public static void createAppender(String name, AppenderType type, String fileName) {
        if (type == null) {
            throw new IllegalArgumentException("Parameter type is null for the appender " + name);
        }
        Appender appender;
        switch (type) {
            case FILE:
                if (StringUtils.isEmpty(fileName)) fileName = "mylog.txt";
                appender = new FileAppender(name, fileName);
                break;
            case CONSOLE:
            default:
                appender = new ConsoleAppender(name);
        }
        addAppender(appender);
    }

}
