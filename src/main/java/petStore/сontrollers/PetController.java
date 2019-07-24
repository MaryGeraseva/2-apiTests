package petStore.сontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import petStore.responses.PetStoreResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import petStore.models.petModel.PetModel;

import static io.restassured.RestAssured.given;

public class PetController extends AbstractController {

    public PetController() {
        RestAssured.requestSpecification = AbstractController.requestSpecBuilder
                .setBasePath("/pet")
                .build();
    }

    public Response addPet(PetModel pet) {
        log.info(String.format("make POST request \n%s", pet.toString()));
        return given()
                    .body(pet)
                .when()
                    .post();
    }

    public Response addPet(JsonNode pet) {
        return given()
                    .body(pet)
                .when()
                    .post();
    }

//    public PetModel addPet(PetModel pet) {
//        return given()
//                    .body(pet)
//                .when()
//                    .post().as(PetModel.class);
//    }

//    public JsonNode addPet(JsonNode pet) {
//        return given()
//                    .body(pet)
//                .when()
//                    .post().as(JsonNode.class);
//    }

    public PetModel updatePet(PetModel pet) {
        return given()
                    .body(pet)
                .when()
                    .put().as(PetModel.class);
    }

    public void deletePet(long id) {
        given()
        .when()
                .delete(String.valueOf(id));
    }

    public Object getPetById(long id) {
        Response response = given()
                            .when()
                                .get(String.valueOf(id));
        response.print();
        if (response.statusCode() == 200) {
            return response.as(PetModel.class);
        } else {
            return response.as(PetStoreResponse.class);
        }
    }

}
