package rest.petStoreTests.PetTests.getTests;

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
public class PetGetPositiveTests extends BaseTest {

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint GET positive test started ")
    @Description(value = "test checks GET request with valid id, " +
            "expected response status code 200 and and well-formed json with test data")
    public void PetGetPositiveTest200(int testId) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        Response response = controller.addPet(pet);
        assertions.assertStatusCode(response, StatusCodes.CODE200);

        response = controller.getResponseById(builder.getPetId());
        assertions.assertResponseBodyAndStatus(response, pet, StatusCodes.CODE200);
    }
}
