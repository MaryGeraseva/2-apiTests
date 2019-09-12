package petStore.сontrollers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import petStore.models.enums.PetFields;
import petStore.сontrollers.enums.PetStoreEndpoints;

import static io.restassured.RestAssured.given;

public class PetListController extends AbstractController {

    public PetListController() {
        RestAssured.requestSpecification = AbstractController.requestSpecBuilder
                .setBasePath(PetStoreEndpoints.PETS_BY_STATUS.getPath())
                .build();
    }

    public Response getPetsByStatus(String statuses) {
        Response response =
                given()
                        .formParam(PetFields.STATUS.getValue(), statuses)
                        .when()
                        .get();
        return response;
    }
}
