package common;

import io.qameta.allure.Allure;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ExtendWith(TestListener.class)
public class BaseTest {
    public Logger log = LogInstance.getLogger();

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        log = LogInstance.setContext(testInfo);
        log.info(String.format("%s setUp", testInfo.getDisplayName()));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        log.info(String.format("%s tearDown", testInfo.getDisplayName()));
        attachLog(testInfo);
    }

    public void attachLog(TestInfo testInfo) {
        try (InputStream inputStream = Files.newInputStream(Paths.get(LogInstance.getLogsFilePath(testInfo)))) {
            Allure.addAttachment("Logs", inputStream);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}
