package rest.petStoreTests.PetTests.headersTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.сontrollers.ModifiablePetController;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetPostWithoutRequiredHeadersTests extends BaseTest {

    @ParameterizedTest(name = "POST without headers #{0}")
    @CsvSource({
            "1, 'api_key', 'maryTest062019'",
            "2, 'Content-Type', 'application/json'"
    })
    @Step("Pet endpoint POST request without required headers test started")
    @Description(value = "test checks POST request without required headers, expected response status code 4XX")
    public void PetPostWithoutRequiredHeaderTests4XX(int testId, String headerName, String headerValue) {
        ModifiablePetController controller = new ModifiablePetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        Response response = controller.addPet(pet, headerName, headerValue);
        assertions.assertStatusCodeIsClientError(response);
    }
}
