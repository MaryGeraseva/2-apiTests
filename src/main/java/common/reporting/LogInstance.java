package common.reporting;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.TestInfo;


public class LogInstance {

    private static ThreadLocal<Logger> log = new ThreadLocal<>();

    private LogInstance() {
    }

    public static synchronized Logger getLogger() {
        if (log.get() == null) {
            log.set(LogManager.getLogger("Logger"));
        }
        return log.get();
    }

    public static synchronized Logger setContext(TestInfo testInfo) {
        Logger logger = LogManager.getLogger(testInfo.getDisplayName());
        logger.addAppender(LogAppender.fileAppenderConfig(testInfo));
        logger.addAppender(LogAppender.consoleAppenderConfig());
        log.set(logger);
        return log.get();
    }

    public static void resetLog() {
        log.set(null);
    }
}
