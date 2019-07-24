package petStore.assertions;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import common.reporting.LogInstance;
import petStore.responses.PetStoreResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import petStore.models.petModel.PetModel;

public class PetAssertions {

    private Logger log = LogInstance.getLogger();

    @Step("check responses status code")
    public void assertStatusCode(Response response, int expectedCode) {
        int actualCode = response.getStatusCode();
        Assertions.assertEquals(expectedCode, actualCode,
                String.format("didn't get expected result, actual status code is %s", actualCode));
        log.info(String.format("got status code %s", actualCode));
    }

    @Step("check responses status code")
    public void assertResponseBody(Response response, JsonNode expectedPet) {
        JsonNode actualPet = response.as(JsonNode.class);
        Assertions.assertEquals(expectedPet, actualPet, "didn't get expected result");
        log.info(String.format("got responses %s", actualPet.toString()));
    }

    @Step("check responses status code, type, message")
    public void assertResponseBody(Response response, PetStoreResponse expectedResponse) {
        PetStoreResponse actualResponse = response.as(PetStoreResponse.class);
        Assertions.assertEquals(expectedResponse, actualResponse, "didn't get expected result");
        log.info(String.format("got responses %s", actualResponse.toString()));
    }






}
