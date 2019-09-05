package rest.petStoreTests.PetTests.deleteTests;

import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.DataGenerator;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetDeleteNegativeTests extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint DELETE not found test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint DELETE not found test started ")
    @Description(value = "test checks DELETE request with nonexistent id, " +
            "expected response status code 404 and \"Pet not found\" message")
    public void PetDeleteNotFoundTest404(int testId) {
        controller = new PetController();
        assertions = new PetAssertions();

        String petId = DataGenerator.getRandomId();
        response = controller.deletePet(petId);

        response = controller.deletePet(petId);
        assertions.assertResponseMessageAndStatus(response, StatusCodes.CODE404);
    }

    @ParameterizedTest(name = "Pet endpoint DELETE invalid id test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#invalidId")
    @Step("Pet endpoint DELETE invalid id test started ")
    @Description(value = "test checks DELETE request with invalid id, " +
            "expected response status code 400 and \"Invalid ID supplied\" message")
    public void PetDeleteInvalidId400(int testId, String id) {
        controller = new PetController();
        assertions = new PetAssertions();

        response = controller.deletePet(id);
        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE400);
    }
}
