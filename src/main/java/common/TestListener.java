package common;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestListener implements TestWatcher, BeforeEachCallback {

    private Logger log;

    @Override
    public void beforeEach(ExtensionContext context) {
        this.log = LogInstance.getLogger();
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info(String.format("%s disabled", context.getDisplayName()));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info(String.format("%s finished successfully", context.getDisplayName()));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info(String.format("%s aborted", context.getDisplayName()));
        getErrorLog(cause);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        getErrorLog(cause);
        log.info(String.format("%s failed", context.getDisplayName()));
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
