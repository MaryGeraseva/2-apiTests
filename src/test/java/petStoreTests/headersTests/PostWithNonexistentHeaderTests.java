package petStoreTests.headersTests;

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
import petStore.responses.StatusCodes;
import petStore.сontrollers.ModifiablePetController;

import java.util.Map;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class PostWithNonexistentHeaderTests extends BaseTest {

    @ParameterizedTest(name = "POST with nonexistent header #{0}")
    @MethodSource("petStoreTests.testData.DataProvider#nonexistentHeaders")
    @Step("Pet endpoint POST request with additional nonexistent header test started")
    @Description(value = "test checks POST request with additional nonexistent header, " +
            "expected response status code 200, message\"OK\" and and well-formed json with test data")
    public void postWithNonexistentHeaderTests200(int testId, Map<String, String> headers) {
        ModifiablePetController controller = new ModifiablePetController();
        PetAssertions assertions = new PetAssertions();
        PetBuilderJackson builder = new PetBuilderJackson();

        ObjectNode pet = builder.withAllFields().build();
        Response response = controller.addPet(pet, headers);
        assertions.assertResponseBodyAndStatus(response, pet, StatusCodes.CODE200);
    }
}
