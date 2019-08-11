package rest.petStoreTests.PetTests.getTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

public class GetNegativeTests extends BaseTest {

    private Response postResponse;
    private Response putResponse;
    private PetController controller;
    private PetAssertions assertions;
    private PetBuilderJackson builder;

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#invalidId")
    @Description(value = "test checks GET request with invalid id, " +
            "expected response status code 405 and Invalid ID supplied")
    @Step("Pet endpoint GET positive test started ")
    public void PetPutInvalidId405(int testId, String id) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        builder.setPetId(id);
        putResponse = controller.updatePet(pet);

        assertions.assertStatusCode(putResponse, StatusCodes.CODE404.getCode());
    }
}
