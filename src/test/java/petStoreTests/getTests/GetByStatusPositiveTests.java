package petStoreTests.getTests;

import common.BaseTest;
import common.reporting.ReplaceCamelCase;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import petStore.assertions.PetAssertions;
import petStore.responses.StatusCodes;
import petStore.сontrollers.PetListController;


@DisplayNameGeneration(ReplaceCamelCase.class)
public class GetByStatusPositiveTests extends BaseTest {

    @ParameterizedTest(name = "GET by status positive test #{0}")
    @CsvSource({
            "1, 'available,pending,sold'",
            "2, 'available,pending'",
            "3, 'available,sold'",
            "4, 'pending,sold'",
            "5, 'available'",
            "6, 'pending'",
            "7, 'sold'"
    })
    @Step("Pet endpoint GET pet list by status positive test started")
    @Description(value = "test checks GET pet list request with valid \"status\" field value, " +
            "expected response status code 200, message \"OK\" and and well-formed json body")
    public void getByStatusPositiveTest200(int testId, String status) {
        PetListController controller = new PetListController();
        PetAssertions assertions = new PetAssertions();

        Response response = controller.getPetsByStatus(status);
        assertions.assertResponseMessageAndStatus(response, StatusCodes.CODE200);
        assertions.assertPetsByStatusInList(response, status);
    }
}
