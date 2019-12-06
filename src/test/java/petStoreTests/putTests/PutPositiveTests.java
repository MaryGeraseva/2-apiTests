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
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PutPositiveTests extends BaseTest {

    @ParameterizedTest(name = "PUT positive test #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#putPositive")
    @Step("Pet endpoint PUT positive test started")
    @Description(value = "test checks PUT request with valid data, " +
            "expected response status code 200, message \"OK\" and well-formed json with test data")
    public void putPositiveTest200(int testId, String field, String nestedField) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        Response postResponse = controller.addPet(pet);

        assertions.assertStatusCode(postResponse, StatusCodes.CODE200);

        ObjectNode updatedPet = builder.getUpdatedPet(pet, field, nestedField).build();
        Response putResponse = controller.updatePet(updatedPet);

        assertions.assertResponseBodyAndStatus(putResponse, updatedPet, StatusCodes.CODE200);
    }
}
