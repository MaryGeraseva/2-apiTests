package rest.petStoreTests.PetTests.putTests;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

public class PetPutNegativeTests extends BaseTest {

    @ParameterizedTest(name = "Pet endpoint POST negative test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#negativeWithJsonData")
    @Step("Pet endpoint POST negative test started ")
    @Description(value = "test checks POST request with empty request body, with additional nonexistent field," +
            "without required fields, expected result status code 405")
    public void PetPutNegativeTest(int testId, String json) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();

        Response response = controller.updatePet(json);
        assertions.assertStatusCode(response,  StatusCodes.CODE405_PUT.getCode());
        assertions.assertResponseBody(response, StatusCodes.CODE405_PUT);
    }
}
