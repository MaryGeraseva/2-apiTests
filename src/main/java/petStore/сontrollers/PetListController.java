package petStore.сontrollers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import petStore.models.petModel.PetModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetListController extends AbstractController {

    public PetListController() {
        RestAssured.requestSpecification = AbstractController.requestSpecBuilder
                .setBasePath("/pet/findByStatus")
                .build();
    }

    public List<PetModel> getPetsByStatus(String statuses) {
        List<PetModel> petList = new ArrayList<>();
        Response response =
                given()
                        .formParam("status", statuses)
                        .when()
                        .get();
        ObjectMapper mapper = new ObjectMapper();
        String s = response.body().asString();
        try {
            petList = mapper.readValue(s, new TypeReference<List<PetModel>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petList;
    }
}
