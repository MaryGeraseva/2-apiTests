package petStore.сontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import common.reporting.LogInstance;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import petStore.сontrollers.enums.PetStoreEndpoints;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ModifiablePetController {

    private static RequestSpecBuilder requestSpecBuilder;
    public Logger log = LogInstance.getLogger();

    static {
        requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(PetStoreEndpoints.URL.getPath())
                .setBasePath(PetStoreEndpoints.PET.getPath())
                .log(LogDetail.ALL).addFilter(new AllureRestAssured());
        RestAssured.defaultParser = Parser.JSON;
//        RestAssured.proxy("localhost" , 8888);
    }

    public Response addPet(JsonNode pet, String headerName, String headerValue) {
        log.info(String.format("make POST request \n%s", pet.toString()));
        RestAssured.requestSpecification = requestSpecBuilder.build();
        return given().header(headerName, headerValue)
                .body(pet)
                .when()
                .post();
    }

    public Response addPet(JsonNode pet, Map<String, String> headers) {
        log.info(String.format("make POST request \n%s", pet.toString()));
        RestAssured.requestSpecification = requestSpecBuilder.build();
        return given().headers(headers)
                .body(pet)
                .when()
                .post();
    }

}
