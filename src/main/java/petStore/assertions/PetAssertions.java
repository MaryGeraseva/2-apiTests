package petStore.assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PetAssertions {

    private Logger log = LogInstance.getLogger();

    @Step("check responses status code")
    public void assertStatusCode(Response response, StatusCodes expectedStatus) {
        int actualCode = response.getStatusCode();
        int expectedCode = expectedStatus.getCode();

        Assertions.assertEquals(expectedCode, actualCode,
                String.format("didn't get expected result, actual status code is %s instead of %s", actualCode, expectedCode));
        log.info(String.format("got status code %s", actualCode));
    }

    @Step("check responses status message")
    public void assertStatusMessage(Response response, StatusCodes expectedStatus) {
        String actualMessage = response.getStatusLine();
        String expectedMessage = expectedStatus.getStatusMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage),
                String.format("didn't get expected result, actual status line \"%s\" doesn't contain message \"%s\"",
                                actualMessage, expectedMessage));
        log.info(String.format("got status message %s", expectedMessage));
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

    @Step("check pet statuses in list")
    public void assertPetsByStatusInList(Response response, String status) {
        List<PetModel> petList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        String s = response.body().asString();
        try {
            petList = mapper.readValue(s, new TypeReference<List<PetModel>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (PetModel pet: petList) {
            Assertions.assertTrue(status.contains(pet.getStatus()),
                    String.format("didn't get expected result, some pet didn't have expected status \"%s\"", status));
        }
        log.info(String.format("all pets in list have expected status \"%s\", pet quantity is %d", status, petList.size()));
    }

    @Step("check responses status code, message and body")
    public void assertResponseBodyAndStatus(Response response, StatusCodes expectedStatus) {
        Assertions.assertAll(
                () -> assertStatusCode(response, expectedStatus),
                () -> assertStatusMessage(response, expectedStatus),
                () -> assertResponseBody(response, expectedStatus)
        );
    }

    @Step("check responses status code, message and body")
    public void assertResponseBodyAndStatus(Response response, JsonNode requestPet, StatusCodes expectedStatus) {
        Assertions.assertAll(
                () -> assertStatusCode(response, expectedStatus),
                () -> assertStatusMessage(response, expectedStatus),
                () -> assertResponseBody(response, requestPet)
        );
    }

    @Step("check responses status code and message")
    public void assertResponseMessageAndStatus(Response response, StatusCodes expectedStatus) {
        Assertions.assertAll(
                () -> assertStatusCode(response, expectedStatus),
                () -> assertStatusMessage(response, expectedStatus)
        );
    }

}
