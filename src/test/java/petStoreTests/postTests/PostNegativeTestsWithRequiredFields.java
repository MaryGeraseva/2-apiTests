package petStoreTests.postTests;

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
public class PostNegativeTestsWithRequiredFields extends BaseTest {

    @ParameterizedTest(name = "POST negative test with required fields #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#postRequiredFieldsStatus405")
    @Step("Pet endpoint POST negative test with required fields and headers started")
    @Description(value = "test checks POST request with headers and required fields " +
            " with invalid data, expected response status code 405 and message \"Invalid input\"")
    public void postNegativeTestsWithRequiredFields405(int testId, String petId, String categoryId,
                                                       String categoryName, String petStatus) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson().withRequiredFields(petId, categoryId, categoryName, petStatus).build();
        Response response = controller.addPet(pet);

        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE405_POST);
    }
}
