package petStoreTests.notAllowedMethodTests;

import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import petStore.assertions.PetAssertions;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class NotAllowedMethodTests extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @Test
    @DisplayName("PATCH test")
    @Step("Pet endpoint not allowed method PATCH test started")
    @Description(value = "test checks not allowed method PATCH, " +
            "expected response status code 405 and \"Method Not Allowed\" message")
    public void patchTest405() {
        controller = new PetController();
        assertions = new PetAssertions();

        response = controller.patchPet();
        assertions.assertResponseMessageAndStatus(response, StatusCodes.CODE405);
    }

    @Test
    @DisplayName("HEAD test")
    @Step("Pet endpoint not allowed method HEAD test started")
    @Description(value = "test checks not allowed method HEAD, " +
            "expected response status code 405 and \"Method Not Allowed\" message")
    public void headTest405() {
        controller = new PetController();
        assertions = new PetAssertions();

        response = controller.headPet();
        assertions.assertResponseMessageAndStatus(response, StatusCodes.CODE405);
    }
}
