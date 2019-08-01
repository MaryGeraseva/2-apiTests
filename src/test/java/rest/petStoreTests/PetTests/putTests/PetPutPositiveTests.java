package rest.petStoreTests.PetTests.PutTests;

import common.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import petStore.assertions.PetAssertions;
import petStore.сontrollers.PetController;

public class PetPutPositiveTest extends BaseTest {

    private Response response;
    private PetController controller;
    private PetAssertions assertions;

    @ParameterizedTest(name = "Pet endpoint PUT positive test #{0}")
    @MethodSource("rest.petStoreTests.PetTests.PetTestsDataProvider#")
    @Step("Pet endpoint PUT positive test started ")
    @Description(value = "test checks PUT request with valid data, " +
                         "expected response status code 200 and well-formed json with test data")
    public void PetPostEmptyBodyTest(int testId, String json) {

    }



}
