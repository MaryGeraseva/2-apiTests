package rest.petStoreTests.PetTests.headersTests;

import com.fasterxml.jackson.databind.node.ObjectNode;
import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.models.builders.PetBuilderJackson;
import petStore.сontrollers.ModifiablePetController;

import java.util.Map;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PetPostWithInvalidHeaderValueTests extends BaseTest {

    @ParameterizedTest(name = "POST with invalid header #{0}")
    @MethodSource("rest.petStoreTests.PetTests.dataProviders.PetDataProvider#invalidHeaders")
    @Step("Pet endpoint POST request with invalid header value test started")
    @Description(value = "test checks POST request with invalid header value, expected response status code 4XX")
    public void PetPostWithInvalidHeaderValueTests4XX(int testId, Map<String, String> headers) {
        ModifiablePetController controller = new ModifiablePetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        Response response = controller.addPet(pet, headers);
        assertions.assertStatusCodeIsClientError(response);
    }
}
