package petStoreTests.postTests;

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
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

import java.util.List;
import java.util.Map;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PostNegativeTestsWithAllFields extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "POST negative test with all fields #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#postAllFieldsStatus405")
    @Step("Pet endpoint POST negative test with all fields and headers started")
    @Description(value = "test checks POST request with headers and all fields " +
            " with invalid data, expected response status code 405 and message \"Invalid input\"")
    public void postNegativeTestsWitAllFields405(int testId, String petId, String categoryId, String categoryName,
                                                    String name, List<String> photoUrls, Map<String, String> items, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson()
                .withAllFields(petId, categoryId, categoryName, name, photoUrls, items, petStatus).build();
        response = controller.addPet(pet);

        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE405_POST);
    }

    @ParameterizedTest(name = "POST pair zero test #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#postPairZeroValue")
    @Step("Pet endpoint POST negative test with all fields and headers started")
    @Description(value = "test checks POST request with headers and all fields which have pair zero values")
    public void postNegativePairZeroTests(int testId, String petId, ObjectNode category, String name,
                                             ArrayNode photoUrls, ArrayNode items, String petStatus, StatusCodes expectedStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson()
                .withAllFields(petId, category, name, photoUrls, items, petStatus).build();
        response = controller.addPet(pet);


        if (response.statusCode() == 200) {
            assertions.assertResponseBody(response, pet);
        }
        assertions.assertStatusCode(response, expectedStatus);
    }
}
