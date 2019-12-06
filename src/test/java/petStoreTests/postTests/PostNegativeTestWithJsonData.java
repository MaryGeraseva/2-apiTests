package petStoreTests.postTests;

import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PostNegativeTestWithJsonData extends BaseTest {

    @ParameterizedTest(name = "POST negative JSON test #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#negativeWithJsonData")
    @Step("Pet endpoint POST negative test started")
    @Description(value = "test checks POST request with empty request body, with additional nonexistent field," +
            "without required fields, expected result status code 405 and message \"Invalid input\"")
    public void postNegativeTestWithJsonData405(int testId, String json) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();

        Response response = controller.addPet(json);
        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE405_POST);
    }
}
