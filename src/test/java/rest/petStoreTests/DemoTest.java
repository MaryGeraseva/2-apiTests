package rest.petStoreTests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import petStore.models.petModel.PetModel;
import petStore.сontrollers.PetController;

import java.util.ArrayList;

public class DemoTest {

    @Test
    public void demoTest() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode pet1 = mapper.createObjectNode()
                .put("id","55555")
                .put("name","layka")
                .put("tag","layka")
                .put("status","layka");




//        PetModel pet1 = new PetModel(new ArrayList<>(), "generatedString", 6880758l, null, new ArrayList<>(), "test22");
        PetModel pet2 = new PetModel(new ArrayList<>(), "generatedString", 6880758l, null, new ArrayList<>(), "test22");
//        PetModel pet3 = new PetModel(new ArrayList<>(), "buba3", 3, null, new ArrayList<>(), PetStatus.AVAILABLE.name().toLowerCase());
       PetController controller = new PetController();
       controller.addPet(pet1);
       controller.addPet(pet2);
       controller.getPetById("55555");
//        Object result = controller.getPetById("6880758");
//        String testdata = "" + "," + PetStatus.SOLD.name().toLowerCase()+ "," + "";

//        controller.getPetsByStatus("test");
//        Assertions.assertEquals(pet, testPet);
//        System.out.println(result);
//        controller.deletePet();
//        result = controller.getPetById();
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//        try {
//            Inventory test = new StoreController().getAllStoreInventory();
//        } catch (Exception e) {
//            Assertions.assertTrue(false,
//                    "\nDidn't get expected result."
//                            + "\nDeserialization failed."
//                            + "\nResponse doesn't match to expected json schema\n"
//                            + e.getMessage());
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////

//        new StoreController().getAllStoreInventoryList();

//
//        StoreOrderModel order = new StoreOrderModel(6880758l, 23, 3l, "2019-23-11", false, "доставка");
//        StoreController storeController = new StoreController();
//        StoreOrderModel response = storeController.makeOrder(order);
//        System.out.println(response);
//        System.out.println(storeController.getOrderById("3"));
//        storeController.deleteOrder("3");
//        System.out.println(storeController.getOrderById("3"));




    }

}

