package rest.petStoreTests.PetTests;

import com.fasterxml.jackson.databind.JsonNode;
import common.BaseTest;
import petStore.responses.PetStoreResponse;
import io.restassured.response.Response;
import petStore.assertions.PetAssertions;
import petStore.models.petModel.PetModel;
import petStore.сontrollers.PetController;

public class PetBaseTest extends BaseTest {

    protected Response response;
    protected PetController controller;
    protected PetAssertions assertions;
    protected PetModel pet;
    protected JsonNode invalidPet;
    protected PetStoreResponse expectedResponse;


}
