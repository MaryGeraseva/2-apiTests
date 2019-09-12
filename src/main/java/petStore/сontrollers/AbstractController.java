package petStore.сontrollers;

import common.reporting.LogInstance;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;
import org.apache.log4j.Logger;
import petStore.сontrollers.enums.PetStoreEndpoints;
import petStore.сontrollers.enums.PetStoreHeaders;


public abstract class AbstractController {

    public static RequestSpecBuilder requestSpecBuilder;
    public Logger log = LogInstance.getLogger();

    static {
        requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(PetStoreEndpoints.URL.getPath())
                .addHeader(PetStoreHeaders.CONTENT_TYPE.getHeaderName(), PetStoreHeaders.CONTENT_TYPE.getHeaderValue())
                .addHeader(PetStoreHeaders.KEY.getHeaderName(), PetStoreHeaders.KEY.getHeaderValue())
                .log(LogDetail.ALL).addFilter(new AllureRestAssured());

        RestAssured.defaultParser = Parser.JSON;
//        RestAssured.proxy("localhost" , 8888);
    }
}