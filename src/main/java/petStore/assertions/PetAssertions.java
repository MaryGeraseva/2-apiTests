package petStore.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.reporting.LogInstance;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import petStore.models.petModel.PetModel;
import petStore.responses.PetStoreResponse;
import petStore.responses.StatusCodes;

public class PetAssertions {

    private Logger log = LogInstance.getLogger();

    @Step("check responses status code")
    public void assertStatusCode(Response response, int expectedCode) {
        int actualCode = response.getStatusCode();

        Assertions.assertEquals(expectedCode, actualCode,
                String.format("didn't get expected result, actual status code is %s", actualCode));
        log.info(String.format("got status code %s", actualCode));
    }

    @Step("check responses body")
    public void assertResponseBody(Response response, JsonNode requestPet) {
        PetModel actualPet = response.as(PetModel.class);
        PetModel expectedPet = null;

        try {
            expectedPet = new ObjectMapper().treeToValue(requestPet, PetModel.class);

        } catch (JsonProcessingException e) {
            log.error("conversion error from JsonNode to PetModel");
            e.printStackTrace();
        }

        Assertions.assertEquals(expectedPet, actualPet, "didn't get expected result");
        log.info(String.format("got responses %s", actualPet.toString()));
    }

    @Step("check responses status code, type, message")
    public void assertResponseBody(Response response, StatusCodes expectedCode) {
        PetStoreResponse actualResponse = response.as(PetStoreResponse.class);
        PetStoreResponse expectedResponse = new PetStoreResponse(expectedCode);

        Assertions.assertEquals(expectedResponse, actualResponse, "didn't get expected result");
        log.info(String.format("got responses %s", actualResponse.toString()));
    }

    @Step("check field case sensitive")
    public void assertCaseSensitive(Response response, JsonNode pet, String field) {
        String expectedField = pet.get(field).asText().toLowerCase();
        String actualField = response.as(JsonNode.class).get(field).asText();

        Assertions.assertEquals(expectedField, actualField, "didn't get expected result");
        log.info(String.format("expected field: %s, actual field: %s", expectedField, actualField));
    }

    @Step("check field case sensitive")
    public void assertCaseSensitive(Response response, JsonNode pet, String field, String nestedField) {
        String expectedField = pet.get(field).get(nestedField).asText().toLowerCase();
        String actualField = response.as(JsonNode.class).get(field).get(nestedField).asText();

        Assertions.assertEquals(expectedField, actualField, "didn't get expected result");
        log.info(String.format("expected field: %s, actual field: %s", expectedField, actualField));
    }

    @Step("check responses status code, type, message")
    public void assertResponseAndStatusCode(Response response, StatusCodes expectedCode) {
        Assertions.assertAll(
                ()-> assertStatusCode(response, expectedCode.getCode()),
                () ->assertResponseBody(response, expectedCode)
        );
    }

}
