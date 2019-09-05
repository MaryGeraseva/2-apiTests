package rest.petStoreTests.PetTests.putTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

public class PetPutPositiveTests extends BaseTest {

    @ParameterizedTest(name = "Pet endpoint PUT status 200 #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#putPositive")
    @Step("Pet endpoint PUT positive test started ")
    @Description(value = "test checks PUT request with valid data, " +
                         "expected response status code 200 and well-formed json with test data")
    public void PetPutPositiveTest200(int testId, String field, String nestedField) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        Response postResponse = controller.addPet(pet);

        assertions.assertStatusCode(postResponse, StatusCodes.CODE200);

        ObjectNode updatedPet = builder.getUpdatedPet(pet, field, nestedField).build();
        Response putResponse = controller.updatePet(updatedPet);

        assertions.assertStatusCode(putResponse, StatusCodes.CODE200);
        assertions.assertResponseBody(putResponse, updatedPet);
    }
}
