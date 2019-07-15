package petStore.сontrollers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.response.PetStoreResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import petStore.models.petModel.PetModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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


    public List<PetModel> getPetsByStatus(String statuses) {
        List<PetModel> petlist = new ArrayList<>();
        Response response =
                given()
                    .spec(new RequestSpecBuilder().setBasePath("/pet/findByStatus").build())
                    .formParam("status", statuses)
                .when()
                    .get();
        ObjectMapper mapper = new ObjectMapper();
        String s = response.body().asString();
        try {
            petlist = mapper.readValue(s, new TypeReference<List<PetModel>>(){});
//            for(PetModel pet : petlist) {
//                System.out.println(pet);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petlist;
    }
}
