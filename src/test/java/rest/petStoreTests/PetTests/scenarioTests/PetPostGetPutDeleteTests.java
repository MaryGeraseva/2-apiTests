package rest.petStoreTests.PetTests.scenarioTests;

import com.fasterxml.jackson.databind.JsonNode;
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
public class PetPostGetPutDeleteTests extends BaseTest {

    @ParameterizedTest(name = "Pet endpoint POST-GET-PUT-DELETE test #{0}")
    @ValueSource(ints = {1, 2, 3})
    @Step("Pet endpoint POST-GET-PUT-DELETE started ")
    @Description(value = "test checks POST-GET-PUT-DELETE requests with valid data")
    public void PetPostGetPutDeleteTest(int testId) {
        PetController controller = new PetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        JsonNode pet = builder.withAllFields().build();
        String petId = builder.getPetId();

        Response response = controller.addPet(pet);
        assertions.assertResponseBodyAndStatus(response, pet, StatusCodes.CODE200);

        response = controller.getPetById(petId);
        assertions.assertResponseBodyAndStatus(response, pet, StatusCodes.CODE200);

        JsonNode updatedPet = builder.withAllFields().build();
        builder.setPetId(petId);
        response = controller.updatePet(updatedPet);
        assertions.assertResponseBodyAndStatus(response, updatedPet, StatusCodes.CODE200);

        response = controller.deletePet(petId);
        assertions.assertResponseMessageAndStatus(response, StatusCodes.CODE200);

        response = controller.getPetById(petId);
        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE404);
    }
}
