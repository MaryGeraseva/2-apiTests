package petStore.сontrollers;

import common.reporting.LogInstance;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.apache.log4j.Logger;


public abstract class AbstractController {

    public static RequestSpecBuilder requestSpecBuilder;
    public Logger log = LogInstance.getLogger();

    static {
        requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType(ContentType.JSON)
                .addHeader("api_key", "maryTest062019")
                .addHeader("Accept", ContentType.JSON.toString())
                .log(LogDetail.ALL);

        RestAssured.defaultParser = Parser.JSON;
//        RestAssured.proxy("localhost" , 8888);

//        RestAssured.responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }
}