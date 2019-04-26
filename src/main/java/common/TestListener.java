package common;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class TestListener implements TestWatcher, BeforeEachCallback {

    private Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        ThreadContext.put("threadId", String.valueOf(Thread.currentThread().getId()));
        log.info(String.format("%s started", context.getDisplayName()));
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
