package com.mylogger.config;

import com.mylogger.appender.AppenderRef;
import com.mylogger.appender.AppenderType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertiesConfigurationLoader {

    private String propertiesFilePath;

    public PropertiesConfigurationLoader(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;

    }

    public void loadConfigurations() {

        Properties properties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(this.propertiesFilePath)) {
            properties.load(in);
            System.out.println("Properties loaded... " + properties);

            //appender
            Properties appenderSubset = extractSubset("appender", properties);
            Map<String, Properties> appenderProperties = partitionOnCommonPrefixes(appenderSubset);

            for (Map.Entry<String, Properties> e : appenderProperties.entrySet()) {
                createAppender(e.getKey().trim(), e.getValue());
            }

            //logger
            Properties loggerSubset = extractSubset("logger", properties);
            Map<String, Properties> loggerProperties = partitionOnCommonPrefixes(loggerSubset);

            for (Map.Entry<String, Properties> e : loggerProperties.entrySet()) {
                createLogger(e.getKey().trim(), e.getValue());
            }

            //rootLogger
            String rootProp = properties.getProperty("rootLogger");
            Properties props = extractSubset("rootLogger", properties);
            if (rootProp != null) {
                props.setProperty("", rootProp);
                properties.remove("rootLogger");
            }

            if (props.size() > 0) {
                createRootLogger(props);
            }

        } catch (IOException e) {
            System.out.println("Failed to load properties file... " + e.getMessage());
        }
    }

    private void createAppender(String key, Properties properties) {
        String appenderName = StringUtils.trimToNull((String) properties.remove("name"));
        if (StringUtils.isEmpty(appenderName)) {
            System.out.println("No name attribute provided for appender " + key);
            return;
        }

        String appenderType = StringUtils.trimToNull((String) properties.remove("type"));
        if (StringUtils.isEmpty(appenderType)) {
            System.out.println("No type attribute provided for appender " + key);
            return;
        }

        String fileName = StringUtils.trimToNull((String) properties.remove("fileName"));

        LogManager.createAppender(appenderName, AppenderType.getAppender(appenderType), fileName);
    }

    private void createRootLogger(Properties properties) {
        String levelStr = StringUtils.trimToNull((String) properties.remove("level"));
        LogLevel level = LogLevel.getLevel(levelStr);
        List<AppenderRef> refs = addAppendersRefs(properties);

        LevelAndRef container = addLevelAndRefs(properties, level, refs);
        LogManager.createRootLogger(container.getLevel(), container.getRefs());
    }

    private LevelAndRef addLevelAndRefs(Properties properties, LogLevel level, List<AppenderRef> appenderRefs) {
        String levelAndRefs = properties.getProperty("");
        LevelAndRef res = new LevelAndRef();
        if (levelAndRefs != null) {
            String[] parts = StringUtils.split(levelAndRefs, "\\s*,\\s*");
            res.setLevel(LogLevel.getLevel(parts[0]));
            if (parts.length > 1) {
                List<AppenderRef> refs = new ArrayList<>();
                Arrays.stream(parts).skip(1L).map(StringUtils::trimToNull).forEach(ref -> refs.add(createAppenderRef(ref)));
                res.setRefs(refs);
            }
        } else {
            res.setLevel(level);
            res.setRefs(appenderRefs);
        }
        return res;
    }

    private List<AppenderRef> addAppendersRefs(Properties properties) {
        Properties subset = extractSubset("appenderRef", properties);
        Map<String, Properties> parts = partitionOnCommonPrefixes(subset);
        List<AppenderRef> appenderRefs = new ArrayList<>();
        for (Map.Entry<String, Properties> e : parts.entrySet()) {
            appenderRefs.add(createAppenderRef(e.getKey(), e.getValue()));
        }
        return appenderRefs;
    }

    private AppenderRef createAppenderRef(String key, Properties properties) {
        String ref = (String) properties.remove("ref");
        if (StringUtils.isEmpty(ref)) {
            throw new IllegalArgumentException("No ref attribute provided for AppenderRef " + key);
        } else {
            return new AppenderRef(ref);
        }
    }

    private AppenderRef createAppenderRef(String ref) {
        return new AppenderRef(ref);
    }

    protected void createLogger(String key, Properties properties) {
        String loggerName = StringUtils.trimToNull((String) properties.remove("name"));
        if (StringUtils.isEmpty(loggerName)) {
            System.out.println("No name attribute provided for logger " + key);
            return;
        }
        String level = StringUtils.trimToNull((String) properties.remove("level"));
        LogLevel logLevel = LogLevel.getLevel(level);
        List<AppenderRef> appenderRefs = getLoggerAppenderRefs(properties);
        String additivityStr = StringUtils.trimToNull((String) properties.remove("additivity"));
        boolean additivity = StringUtils.isEmpty(additivityStr) || !StringUtils.equalsIgnoreCase("false", additivityStr);
        LogManager.createLogger(loggerName, logLevel, appenderRefs, additivity);
    }

    private List<AppenderRef> getLoggerAppenderRefs(Properties properties) {
        List<AppenderRef> appenderRefs = new ArrayList<>();
        Map<String, Properties> appenderRefProps = partitionOnCommonPrefixes(extractSubset("appenderRef", properties));
        for (Map.Entry<String, Properties> entry : appenderRefProps.entrySet()) {
            appenderRefs.add(this.createAppenderRef(entry.getKey().trim(), entry.getValue()));
        }
        return appenderRefs;
    }

    private static Map<String, Properties> partitionOnCommonPrefixes(Properties properties) {
        Map<String, Properties> propertiesMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            int idx = key.indexOf('.');
            if (idx >= 0) {
                String prefix = key.substring(0, idx);
                if (!propertiesMap.containsKey(prefix))
                    propertiesMap.put(prefix, new Properties());
                propertiesMap.get(prefix).setProperty(key.substring(idx + 1), properties.getProperty(key));
            }
        }
        return propertiesMap;
    }

    private static Properties extractSubset(String prefix, Properties properties) {
        Properties subset = new Properties();
        if (!StringUtils.isEmpty(prefix)) {
            String prefixToMatch = prefix + ".";
            List<String> keysToRemove = new ArrayList<>();
            for (String key : properties.stringPropertyNames()) {
                if (key.startsWith(prefixToMatch)) {
                    subset.setProperty(key.substring(prefixToMatch.length()), properties.getProperty(key));
                    keysToRemove.add(key);
                }
            }

            for (String key : keysToRemove) {
                properties.remove(key);
            }
        }
        return subset;
    }
}
