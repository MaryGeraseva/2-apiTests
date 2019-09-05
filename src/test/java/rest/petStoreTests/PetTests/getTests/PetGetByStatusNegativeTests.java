package rest.petStoreTests.PetTests.getTests;

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
public class PetGetByStatusNegativeTests extends BaseTest {

    @ParameterizedTest(name = "Pet endpoint GET pet list by status negative test #{0}")
    @CsvSource({
            "1, 'testStatus'",
            "2, 'AVAILABLE'",
            "3, 'null'",
            "4, ''",
            "5, '/'"
    })
    @Step("Pet endpoint GET pet list by status negative test started ")
    @Description(value = "test checks GET request with invalid \"status\" field value, " +
            "expected response status code 400 and and message \"Invalid status value\"")
    public void PetGetByStatusNegativeTest200(int testId, String status) {
        PetListController controller = new PetListController();
        PetAssertions assertions = new PetAssertions();

        Response response = controller.getPetsByStatus(status);
        assertions.assertResponseBodyAndStatus(response, StatusCodes.CODE400_GET_LIST);
    }
}
