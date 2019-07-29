package rest.petStoreTests.PetTests;

import com.fasterxml.jackson.databind.JsonNode;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.PetStoreResponse;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetPostPositiveTestsWithRequiredFields{


    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint POST positive test with required fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#requiredFieldsStatus200")
    @Step("Pet endpoint POST positive test with required fields and headers started")
    @Description(value = "test checks POST request with headers and required fields which have " +
            "valid data, expected response status code 200 and well-formed json with test data")
    public void PetPostPositiveTestsWithRequiredFields200(int testId, String petId, String categoryId,
                                                          String categoryName, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson().withRequiredFields(petId, categoryId, categoryName, petStatus).build();
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, 200);
        assertions.assertResponseBody(response, pet);
    }

    @ParameterizedTest(name = "Pet endpoint POST positive test with required fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#requiredFieldsStatus405")
    @Step("Pet endpoint POST positive test with required fields and headers started")
    @Description(value = "test checks POST request with headers and required fields " +
                         " with data invalid, expected response status code 405")
    public void PetPostPositiveTestsWithRequiredFields405(int testId, String petId, String categoryId,
                                                          String categoryName, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson().withRequiredFields(petId, categoryId, categoryName, petStatus).build();
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, 500);
        assertions.assertResponseBody(response, new PetStoreResponse(StatusCodes.CODE500));
    }
}
