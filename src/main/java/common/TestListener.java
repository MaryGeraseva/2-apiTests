package common;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestListener implements TestWatcher, BeforeEachCallback {

    private static Logger log;

    @Override
    public void beforeEach(ExtensionContext context) {
        log = LogInstance.getLogger();
//        log = LogInstance.setContext(context);
//        log.info(String.format("%s started", context.getDisplayName()));
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info(String.format("%s disabled", context.getDisplayName()));
        LogInstance.resetLog();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info(String.format("%s finished successfully", context.getDisplayName()));
        LogInstance.resetLog();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info(String.format("%s aborted", context.getDisplayName()));
        getErrorLog(cause);
        LogInstance.resetLog();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        getErrorLog(cause);
        log.info(String.format("%s failed", context.getDisplayName()));
        LogInstance.resetLog();
    }

    private void getErrorLog(Throwable cause) {
        log.error(cause.getMessage());
        String stackTrace = "\n";
        for (StackTraceElement element : cause.getStackTrace()) {
            stackTrace += element.toString() + "\n";
        }
        log.error(stackTrace);
    }
}
