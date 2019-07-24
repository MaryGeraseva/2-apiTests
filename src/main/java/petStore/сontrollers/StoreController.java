package petStore.сontrollers;

import com.fasterxml.jackson.databind.JsonNode;
import petStore.responses.PetStoreResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import petStore.models.storeModel.OrderModel;

import static io.restassured.RestAssured.given;

public class StoreController extends AbstractController{

    public StoreController() {
        RestAssured.requestSpecification = AbstractController.requestSpecBuilder
                .setBasePath("/store/order")
                .build();
    }

    public OrderModel makeOrder(OrderModel order) {
        return given()
                .body(order)
                .when()
                .post()
                .as(OrderModel.class);
    }

    public JsonNode makeOrder(JsonNode order) {
        return given()
                .body(order)
                .when()
                .post()
                .then()
                .extract()
                .as(JsonNode.class);
    }

    public Object getOrderById(long id) {
        Response response = given()
                .when()
                .get(String.valueOf(id));
        response.print();
        if(response.statusCode() == 200) {
            return response.as(OrderModel.class);
        } else {
            return response.as(PetStoreResponse.class);
        }
    }

    public void deleteOrder(long id) {
        given()
                .when()
                .delete(String.valueOf(id));
    }
}
