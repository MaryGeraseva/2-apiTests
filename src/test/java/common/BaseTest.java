package common;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@ExtendWith(TestListener.class)
public class BaseTest {
    public static Logger log = LogInstance.getLogger();

    @BeforeEach
    public void setUp(TestInfo testInfo, ExtensionContext context) {
        log = LogInstance.setContext(context);
        log.info(String.format("setUp %s", testInfo.getDisplayName()));
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        log.info(String.format("tearDown %s", testInfo.getDisplayName()));
    }

}
