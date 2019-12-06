package petStoreTests.putTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PutNegativeTests extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;
    private PetBuilderJackson builder;

    @ParameterizedTest(name = "PUT status 404 test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint PUT negative test started")
    @Description(value = "test checks PUT request with nonexistent id, " +
            "expected response status code 404 and \"Pet not found message\"")
    public void putNoFoundTest404(int testId) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode originalPet = builder.withAllFields().build();
        String petId = builder.getPetId();

        response = controller.addPet(originalPet);
        assertions.assertStatusCode(response, StatusCodes.CODE200);

        response = controller.deletePet(petId);
        assertions.assertStatusCode(response, StatusCodes.CODE200);

        ObjectNode updatedPet = builder.withAllFields().build();
        builder.setPetId(petId);
        response = controller.updatePet(updatedPet);

        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE404);
    }

    @ParameterizedTest(name = "PUT status 400 #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#invalidId")
    @Step("Pet endpoint PUT negative test started ")
    @Description(value = "test checks PUT request with invalid id, " +
            "expected response status code 400 and \"Invalid ID supplied\"")
    public void putInvalidId400(int testId, String id) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        builder.setPetId(id);
        response = controller.updatePet(pet);

        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE400);
    }

    @ParameterizedTest(name = "POST negative JSON test #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#negativeWithJsonData")
    @Step("Pet endpoint POST negative test started")
    @Description(value = "test checks POST request with empty request body, with additional nonexistent field," +
            "without required fields, expected result status code 405 and message \"Validation exception\"")
    public void putNegativeTestWithJsonData405(int testId, String json) {
        controller = new PetController();
        assertions = new PetAssertions();

        response = controller.updatePet(json);
        assertions.assertResponseBodyAndStatus(response,  StatusCodes.CODE405_PUT);
    }
}
