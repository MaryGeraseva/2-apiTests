package rest.petStoreTests.PetTests;

import common.reporting.ReplaceCamelCase;
import petStore.models.builders.FakePetBuilder;
import petStore.responses.PetStoreResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.models.petModel.Category;
import petStore.models.petModel.PetModel;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetPostPositiveTestsWithRequiredFields extends PetBaseTest {

    @ParameterizedTest(name = "Pet endpoint POST positive test with required fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#requiredFieldsStatus200")
    @Step("Pet endpoint POST positive test with required fields and headers started")
    @Description(value = "test checks POST request with required fields and headers which have " +
            "valid data, expected responses status code 200 and well-formed json with test data")
    public void PetPostPositiveTestsWithRequiredFields200(int testId, String petId, Category category, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        pet = new PetModel(petId, category, petStatus);
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, 200);
        assertions.assertResponseBody(response, pet);
    }

    @ParameterizedTest(name = "Pet endpoint POST positive test with required fields #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#requiredFieldsStatus405BorderVerification")
    @Step("Pet endpoint POST positive test with required fields and headers started")
    @Description(value = "test checks POST request with required headers and fields " +
            " with border conditions data, expected responses status code 405")
    public void PetPostPositiveTestsWithRequiredFields405BorderVerification(int testId, String petId, Category category, String petStatus) {
        controller = new PetController();
        assertions = new PetAssertions();

        pet = new PetModel(petId, category, petStatus);
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, 500);
        assertions.assertResponseBody(response, new PetStoreResponse(StatusCodes.CODE500));
    }
}
