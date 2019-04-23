package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestListener implements TestWatcher, BeforeEachCallback {

    private Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        log.info(String.format("%s test started", context.getDisplayName()));
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info(String.format("%s disabled", context.getDisplayName()));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info(String.format("%s finished successful", context.getDisplayName()));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info(String.format("%s aborted", context.getDisplayName()));
        getErrorLog(cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info(String.format("%s failed", context.getDisplayName()));
        getErrorLog(cause);
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
