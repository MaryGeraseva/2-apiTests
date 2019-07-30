package rest.petStoreTests.PetTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
import petStore.сontrollers.PetController;

import java.util.List;
import java.util.Map;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetPostPositiveTestsWithAllFields extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint POST positive test with all fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#allFieldsStatus200")
    @Step("Pet endpoint POST positive test with all fields and headers started")
    @Description(value = "test checks POST request with headers and all fields which have " +
            "valid data, expected response status code 200 and well-formed json with test data")
    public void PetPostPositiveTestsWithAllFields200(int testId, String petId, String categoryId, String categoryName,
                                                     String name, List<String> photoUrls, Map<String, String> items, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson()
                .withAllFields(petId, categoryId, categoryName, name, photoUrls, items, petStatus).build();
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, 200);
        assertions.assertResponseBody(response, pet);
    }

    @ParameterizedTest(name = "Pet endpoint POST positive test with all fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#pairZeroValue")
    @Step("Pet endpoint POST positive test with all fields and headers started")
    @Description(value = "test checks POST request with headers and all fields which have pair zero value")
    public void PetPostPositivePairZeroTests(int testId, String petId, ObjectNode category, String name,
                                             ArrayNode photoUrls, ArrayNode items, String petStatus, int expectedCode) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson()
                .withAllFields(petId, category, name, photoUrls, items, petStatus).build();
        response = controller.addPet(pet);


        if (response.statusCode() == 200) {
            assertions.assertResponseBody(response, pet);
        }
        assertions.assertStatusCode(response, expectedCode);
    }
}
