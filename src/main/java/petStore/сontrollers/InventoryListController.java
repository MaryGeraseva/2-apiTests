package petStore.сontrollers;

import io.restassured.RestAssured;
import petStore.models.storeModel.Inventory;

import static io.restassured.RestAssured.given;

public class InventoryListController {

    public InventoryListController() {
        RestAssured.requestSpecification = AbstractController.requestSpecBuilder
                .setBasePath(PetStoreEndpoints.STORE_ALL_INVENTORY.getPath())
                .build();
    }

    /** method gets unexpected result, deserialization doesn't work
     *  cause is bag in pet statuses
     */
    public Inventory getAllStoreInventory() {
        return given()
                .when()
                .get().as(Inventory.class);
    }

    public void getAllStoreInventoryList() {
        given()
                .when()
                .get()
                .then()
                .extract().response().prettyPrint();
    }
}
