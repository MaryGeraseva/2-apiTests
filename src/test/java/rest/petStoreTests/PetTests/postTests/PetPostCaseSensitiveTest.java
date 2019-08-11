package rest.petStoreTests.PetTests.postTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.models.petModel.PetModelJackson;
import petStore.models.enums.PetStatuses;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

public class PetPostCaseSensitiveTest extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint field status case sensitive test #{0}")
    @CsvSource({
            "1, 'status'",
            "2, 'status'",
            "3, 'status'"
    })
    @Step("Pet endpoint field status case sensitive test started")
    @Description(value = "test checks field status case sensitive," +
            " expected result is case independence")
    public void PostCaseSensitiveStatusTest(int testId, String field) {
        controller = new PetController();
        assertions = new PetAssertions();

        ObjectNode pet = new PetBuilderJackson().withAllFields().build();
        pet.put(field, PetStatuses.getRandom().name());
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, StatusCodes.CODE200.getCode());
        assertions.assertCaseSensitive(response, pet, field);
    }

    @ParameterizedTest(name = "Pet endpoint category nested field name case sensitive test #{0}")
    @CsvSource({
            "1, 'category', 'name'",
            "2, 'category', 'name'",
            "3, 'category', 'name'"
    })
    @Step("Pet endpoint category nested field name case sensitive test started")
    @Description(value = "test checks category nested field name case sensitive," +
            " expected result is case independence")
    public void PostCaseSensitiveCategoryNameTest(int testId, String field, String nestedField) {
        controller = new PetController();
        assertions = new PetAssertions();

        ObjectNode pet = new PetBuilderJackson().withAllFields().build();

        PetModelJackson petModelJackson = new PetModelJackson();
        ObjectNode category = petModelJackson.getCategory(
                RandomStringUtils.randomNumeric(3),
                RandomStringUtils.randomAlphabetic(5).toUpperCase());
        pet.set(field, category);
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, StatusCodes.CODE200.getCode());
        assertions.assertCaseSensitive(response, pet, field, nestedField);
    }
}
