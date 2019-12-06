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
public class PostPositiveTestsWithRequiredFields extends BaseTest {

    @ParameterizedTest(name = "POST positive test with required fields #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#postRequiredFieldsStatus200")
    @Step("Pet endpoint POST positive test with required fields and headers started")
    @Description(value = "test checks POST request with headers and required fields which have " +
            "valid data, expected response status code 200, message \"OK\" and well-formed json with test data")
    public void postPositiveTestsWithRequiredFields200(int testId, String petId, String categoryId,
                                                          String categoryName, String petStatus) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();

        JsonNode pet = new PetBuilderJackson().withRequiredFields(petId, categoryId, categoryName, petStatus).build();
        Response response = controller.addPet(pet);

        assertions.assertResponseBodyAndStatus(response, pet, StatusCodes.CODE200);

        response = controller.getPetById(petId);
        assertions.assertResponseBodyAndStatus(response, pet, StatusCodes.CODE200);
    }
}
