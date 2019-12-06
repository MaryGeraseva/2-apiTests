package petStoreTests.postTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.models.enums.PetStatuses;
import petStore.models.petModel.PetModelJackson;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PostCaseSensitiveTest extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Status case sensitive test #{0}")
    @CsvSource({
            "1, 'status'",
            "2, 'status'",
            "3, 'status'"
    })
    @Step("Pet endpoint field status case sensitive test started")
    @Description(value = "test checks field status case sensitive," +
            " expected result is case independence")
    public void postStatusCaseSensitiveTest(int testId, String field) {
        controller = new PetController();
        assertions = new PetAssertions();

        ObjectNode pet = new PetBuilderJackson().withAllFields().build();
        pet.put(field, PetStatuses.getRandom().name());
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, StatusCodes.CODE200);
        assertions.assertCaseSensitive(response, pet, field);
    }

    @ParameterizedTest(name = "Category.name case sensitive test #{0}")
    @CsvSource({
            "1, 'category', 'name'",
            "2, 'category', 'name'",
            "3, 'category', 'name'"
    })
    @Step("Pet endpoint category nested field name case sensitive test started")
    @Description(value = "test checks category nested field name case sensitive," +
            " expected result is case independence")
    public void postCategoryNameCaseSensitiveTest(int testId, String field, String nestedField) {
        controller = new PetController();
        assertions = new PetAssertions();

        ObjectNode pet = new PetBuilderJackson().withAllFields().build();

        PetModelJackson petModelJackson = new PetModelJackson();
        ObjectNode category = petModelJackson.getCategory(
                RandomStringUtils.randomNumeric(3),
                RandomStringUtils.randomAlphabetic(5).toUpperCase());
        pet.set(field, category);
        response = controller.addPet(pet);

        assertions.assertStatusCode(response, StatusCodes.CODE200);
        assertions.assertCaseSensitive(response, pet, field, nestedField);
    }
}
