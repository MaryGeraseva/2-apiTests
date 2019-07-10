package petStore.сontrollers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;


public abstract class AbstractController {

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType(ContentType.JSON)
                .addHeader("api_key", "maryTest062019")
                .log(LogDetail.ALL).build();

        RestAssured.defaultParser = Parser.JSON;
    }
}