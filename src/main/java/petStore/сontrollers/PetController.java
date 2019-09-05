package petStore.сontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import petStore.models.petModel.PetModel;
import petStore.responses.PetStoreResponse;

import static io.restassured.RestAssured.given;

public class PetController extends AbstractController {

    public PetController() {
        RestAssured.requestSpecification = AbstractController.requestSpecBuilder
                .setBasePath(PetStoreEndpoints.PET.getPath())
                .build();
    }

    public Response addPet(JsonNode pet) {
        log.info(String.format("make POST request \n%s", pet.toString()));
        return given()
                    .body(pet)
                .when()
                    .post();
    }

    public Response addPet(String json) {
        log.info(String.format("make POST request \n%s", json));
        return given()
                    .body(json)
                .when()
                    .post();
    }

    public Response updatePet(JsonNode pet) {
        log.info(String.format("make PUT request \n%s", pet.toString()));
        return given()
                    .body(pet)
                .when()
                    .put();
    }

    public Response updatePet(String json) {
        log.info(String.format("make PUT request \n%s", json));
        return given()
                    .body(json)
                .when()
                    .put();
    }

    public Response deletePet(String id) {
        return given()
               .when()
                     .delete(id);
    }

    public Object getPetById(String id) {
        Response response = given()
                            .when()
                                .get(id);
        if (response.statusCode() == 200) {
            return response.as(PetModel.class);
        } else {
            return response.as(PetStoreResponse.class);
        }
    }

    public Response getResponseById(String id) {
        return given()
                .when()
                    .get(id);
    }


}
