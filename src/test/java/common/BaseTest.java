package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestListener.class)
public class BaseTest {
    Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp(TestInfo testInfo) {
        log.info(String.format("setUp %s", testInfo.getDisplayName()));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        log.info(String.format("tearDown %s", testInfo.getDisplayName()));
    }

}
