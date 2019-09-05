package rest.petStoreTests.PetTests.postTests;

import com.fasterxml.jackson.databind.JsonNode;
import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetPostNegativeTestsWithRequiredFields extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint POST positive test with required fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#postRequiredFieldsStatus405")
    @Step("Pet endpoint POST positive test with required fields and headers started")
    @Description(value = "test checks POST request with headers and required fields " +
                         " with invalid data, expected response status code 405")
    public void PetPostNegativeTestsWithRequiredFields405(int testId, String petId, String categoryId,
                                                          String categoryName, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson().withRequiredFields(petId, categoryId, categoryName, petStatus).build();
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, StatusCodes.CODE405_POST);
        assertions.assertResponseBody(response, StatusCodes.CODE405_POST);
    }
}
