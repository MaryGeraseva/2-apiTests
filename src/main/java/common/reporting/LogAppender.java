package common.reporting;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.junit.jupiter.api.TestInfo;

import java.io.File;

public class LogAppender {

    public static FileAppender fileAppenderConfig(TestInfo testInfo) {

        String path = getLogsFilePath(testInfo);

        new File(path);

        FileAppender appender = new FileAppender();
        appender.setFile(path);
        appender.setLayout(new EnhancedPatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"));
        appender.setThreshold(Level.TRACE);
        appender.setAppend(false);
        appender.activateOptions();
        return appender;
    }

    public static ConsoleAppender consoleAppenderConfig() {
        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new EnhancedPatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"));
        consoleAppender.setThreshold(Level.TRACE);
        consoleAppender.activateOptions();

        return consoleAppender;
    }

    private static String getLogsFilePath(TestInfo testInfo) {
        return String.format("%s\\build\\reports\\logsByTestMethod\\%s\\%s.log",
                System.getProperty("user.dir"), testInfo.getTestMethod().get().getName(), testInfo.getDisplayName());
    }
}
