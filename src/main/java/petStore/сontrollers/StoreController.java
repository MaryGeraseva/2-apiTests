package petStore.сontrollers;

import common.response.PetStoreResponse;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import petStore.models.storeModel.Inventory;
import petStore.models.storeModel.StoreOrderModel;

import static io.restassured.RestAssured.given;

public class StoreController extends AbstractController{

    public StoreController() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBasePath("/store/order")
                .build();
    }

    /** method gets unexpected result, deserialization doesn't work
     *  cause is bag in pet statuses
     */
    public Inventory getAllStoreInventory() {
        return given()
                .spec(new RequestSpecBuilder().setBasePath("/store/inventory").build())
                .when()
                .get().as(Inventory.class);
    }

    public void getAllStoreInventoryList() {
        given()
                .spec(new RequestSpecBuilder().setBasePath("/store/inventory").build())
                .when()
                .get()
                .then()
                .extract().response().prettyPrint();
    }

    public StoreOrderModel makeOrder(StoreOrderModel order) {
        return given()
                .body(order)
                .when()
                .post()
                .as(StoreOrderModel.class);
    }

    public Object getOrderById(String id) {
        Response response = given()
                .when()
                .get(id);
        System.out.println(response);
        if(response.statusCode() == 200) {
            return response.as(StoreOrderModel.class);
        } else {
            return response.as(PetStoreResponse.class);
        }
    }

    public void deleteOrder(String id) {
        given()
                .when()
                .delete(id);
    }
}
