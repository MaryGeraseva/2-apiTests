package rest.petStoreTests.PetTests.deleteTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetDeletePositiveTests extends BaseTest {

    @ParameterizedTest(name = "DELETE positive test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint DELETE positive test started ")
    @Description(value = "test checks DELETE request with valid id, " +
            "expected response status code 200 and \"OK\" message")
    public void PetGetPositiveTest200(int testId) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        String petId = builder.getPetId();
        Response response = controller.addPet(pet);
        assertions.assertStatusCode(response, StatusCodes.CODE200);

        response = controller.deletePet(petId);
        assertions.assertResponseMessageAndStatus(response, StatusCodes.CODE200);
    }
}
