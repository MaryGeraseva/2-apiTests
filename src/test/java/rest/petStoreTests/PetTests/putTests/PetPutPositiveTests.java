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

    private Response postResponse;
    private Response putResponse;
    private PetController controller;
    private PetAssertions assertions;
    private PetBuilderJackson builder;

    @ParameterizedTest(name = "Pet endpoint PUT positive test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#putPositive")
    @Step("Pet endpoint PUT positive test started ")
    @Description(value = "test checks PUT request with valid data, " +
                         "expected response status code 200 and well-formed json with test data")
    public void PetPutPositiveTest200(int testId, String field, String nestedField) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        postResponse = controller.addPet(pet);

        assertions.assertStatusCode(postResponse, StatusCodes.CODE200.getCode());

        ObjectNode updatedPet = builder.getUpdatedPet(pet, field, nestedField).build();
        putResponse = controller.updatePet(updatedPet);

        assertions.assertStatusCode(putResponse, StatusCodes.CODE200.getCode());
        assertions.assertResponseBody(putResponse, updatedPet);
    }

    @ParameterizedTest(name = "Pet endpoint PUT positive test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint PUT positive test started ")
    @Description(value = "test checks PUT request with nonexistent id, " +
            "expected response status code 404 and Pet not found message")
    public void PetPutNoFoundTest404(int testId) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();

        postResponse = controller.addPet(pet);
        assertions.assertStatusCode(postResponse, StatusCodes.CODE200.getCode());

        controller.deletePet(builder.getPetId());
        putResponse = controller.updatePet(pet);

        assertions.assertStatusCode(putResponse, StatusCodes.CODE404.getCode());
    }

    @ParameterizedTest(name = "Pet endpoint PUT positive test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#invalidId")
    @Step("Pet endpoint PUT positive test started ")
    @Description(value = "test checks PUT request with invalid id, " +
            "expected response status code 400 and Invalid ID supplied")
    public void PetPutInvalidId400(int testId, String id) {
        controller = new PetController();
        assertions = new PetAssertions();
        builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        builder.setPetId(id);
        putResponse = controller.updatePet(pet);

        assertions.assertStatusCode(putResponse, StatusCodes.CODE400.getCode());
    }
}
