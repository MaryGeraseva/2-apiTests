package common;


import org.apache.log4j.*;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.io.IOException;


public class LogInstance {

    private static ThreadLocal<Logger> log = new ThreadLocal<>();

    private LogInstance() {}

    public static synchronized Logger getLogger() {
        if (log.get() == null) {
            LogManager.getLogger("Logger");
        }
        return log.get();
    }

    public static synchronized Logger setContext(TestInfo testInfo) {
        Logger logger = LogManager.getLogger(testInfo.getDisplayName());
        logger.addAppender(appenderConfig(testInfo));
        log.set(logger);
        return log.get();
    }

    private static FileAppender appenderConfig(TestInfo testInfo) {

        String path = getLogsFilePath(testInfo);

        File file = new File(path);

//        try {
//            File file = new File(path);
//            file.getParentFile().mkdir();
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        FileAppender appender = new FileAppender();
        appender.setFile(path);
        appender.setLayout(new EnhancedPatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"));
        appender.setThreshold(Level.TRACE);
        appender.setAppend(true);
        appender.activateOptions();
        return appender;
    }

    public static String getLogsFilePath(TestInfo testInfo) {
       return String.format("%s\\build\\reports\\logsByTestMethod\\%s\\%s.log",
                System.getProperty("user.dir"), testInfo.getTestMethod().get().getName(), testInfo.getDisplayName());
    }

    public static void resetLog() {
        log.set(null);
    }
}
