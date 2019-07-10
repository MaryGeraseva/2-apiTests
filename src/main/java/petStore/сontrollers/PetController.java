package petStore.сontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import common.response.PetStoreResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import petStore.models.petModel.PetModel;

import static io.restassured.RestAssured.given;

public class PetController extends AbstractController {

    public PetController() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBasePath("/pet")
                .build();
    }

    public PetModel addPet(PetModel pet) {
        return given()
                    .body(pet)
                .when()
                    .post().as(PetModel.class);
    }

    public JsonNode addPet(JsonNode pet) {
        return given()
                    .body(pet)
                .when()
                    .post().as(JsonNode.class);
    }

    public PetModel updatePet(PetModel pet) {
        return given()
                    .body(pet)
                .when()
                    .put().as(PetModel.class);
    }

    public void deletePet(String id) {
        given()
        .when()
                .delete(id);
    }

    public Object getPetById(String id) {
        Response response = given()
                            .when()
                                .get(id);
        response.print();
        if (response.statusCode() == 200) {
            return response.as(PetModel.class);
        } else {
            return response.as(PetStoreResponse.class);
        }
    }

    ////have to parse&safe data for testing
    public void getPetsByStatus(String statuses) {
        given()
                .spec(new RequestSpecBuilder().setBasePath("/pet/findByStatus").build())
                .formParam("status", statuses)
        .when()
                .get()
        .then()
                .extract()
                .response().getBody().prettyPrint();
    }
}
