package rest.petStoreTests.PetTests.getTests;

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

public class GetPositiveTests extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;
    private PetBuilderJackson builder;

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint GET positive test started ")
    @Description(value = "test checks GET request with valid id, " +
            "expected response status code 200 and and well-formed json with test data")
    public void PetGetPositiveTest200(int testId) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        response = controller.addPet(pet);
        assertions.assertStatusCode(response, StatusCodes.CODE200.getCode());

        response = controller.getResponseById(builder.getPetId());
        assertions.assertStatusCode(response, StatusCodes.CODE200.getCode());
        assertions.assertResponseBody(response, pet);
    }

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint GET positive test started ")
    @Description(value = "test checks GET request with nonexistent id, " +
            "expected response status code 404 and Pet not found message")
    public void PetGetNotFoundTest404(int testId) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        response = controller.addPet(pet);
        assertions.assertStatusCode(response, StatusCodes.CODE200.getCode());

        controller.deletePet(builder.getPetId());

        response = controller.getResponseById(builder.getPetId());
//        assertions.assertStatusCode(response, StatusCodes.CODE404.getCode());
//        assertions.assertResponseBody(response, StatusCodes.CODE404);
        assertions.assertResponseAndStatusCode(response, StatusCodes.CODE404);
    }

    @ParameterizedTest(name = "Pet endpoint GET positive test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#invalidId")
    @Step("Pet endpoint GET positive test started ")
    @Description(value = "test checks GET request with invalid id, " +
            "expected response status code 400 and Invalid ID supplied")
    public void PetGetInvalidId400(int testId, String id) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        builder.setPetId(id);
        response = controller.getResponseById(builder.getPetId());

//        assertions.assertResponseBody(response, StatusCodes.CODE400);
//        assertions.assertStatusCode(response, StatusCodes.CODE400.getCode());
        assertions.assertResponseAndStatusCode(response, StatusCodes.CODE400);
    }
}
