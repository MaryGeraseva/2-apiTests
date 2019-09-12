package rest.petStoreTests;

import common.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import petStore.models.enums.PetStatuses;

import java.util.Random;

public class DemoTest extends BaseTest {


    @Test
    public void demoTest() {

//        PetModel pet = new PetBuilder().withRequiredFields(44l, new Category(), "test").build();
//        JsonNode pet = new PetBuilderJackson().withRequiredFields("dddd", "cat", "test").build();

//        PetModel pet1 = new PetModel(new ArrayList<>(), "generatedString", 6880758l, null, new ArrayList<>(), "test22");
//        PetModel pet2 = new PetModel(new ArrayList<>(), "generatedString", 6880758l, null, new ArrayList<>(), "test22");
//        PetModel pet3 = new PetModel(new ArrayList<>(), "buba3", 3, null, new ArrayList<>(), PetStatus.AVAILABLE.name().toLowerCase());

//        PetController controller = new PetController();
//        PetAssertions assertions = new PetAssertions();
//
//       Response response = controller.addPet(pet);
//       assertions.assertStatusCode(response, 200);
//       PetAssertions.assertStatusCode(responses, 200);
//        assertions.assertStatusCode405(responses);
//        System.out.println(responses);
//        System.out.println(responses.getContentType());
//        System.out.println(responses.getStatusLine());
//        System.out.println(responses.getTime());
//        System.out.println(responses.as(PetModel.class));
//        System.out.println(responses.as(JsonNode.class));
//       PetListController controller = new PetListController();
//        System.out.println(controller.addPet(pet));
//       controller.deletePet(pet.getId());
//       PetModel responses = controller.addPet(pet);
//       Assertions.assertEquals(pet, responses, "didn't get expected result");
//        System.out.println(controller.getPetById(pet.getId()));
//       controller.getPetsByStatus("test");
//        Object result = controller.getPetById("6880758");
//        String testdata = "" + "," + PetStatus.SOLD.name().toLowerCase()+ "," + "";

//        List<PetModel> list = controller.getPetsByStatus(PetStatus.SOLD.name().toLowerCase());
//        System.out.println(list.size());
//        Assertions.assertTrue(list.size() != 0);
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
//        OrderModel order = new OrderModel(6880758l, 23, 3l, "2019-23-11", false, "доставка");
//        OrderModel order = new OrderBuilder().withRequiredFields(23l, 14l, 1, OrderStatus.APPROVED.name().toLowerCase(), true).build();
//        JsonNode fakeOrder = new OrderBuilderJackson().withRequiredFields("33", "33", "54", "333", "true").build();
//        StoreController storeController = new StoreController();
//        OrderModel responses = storeController.makeOrder(order);
//        System.out.println(responses);
//        System.out.println(storeController.getOrderById("23"));
//        storeController.deleteOrder("23");
//        System.out.println(storeController.getOrderById("23"));
//        storeController.makeOrder(fakeOrder);
//        storeController.getOrderById("33");
//        storeController.deleteOrder("33");
//        storeController.getOrderById("33");
//
//        PetBuilderJackson builder = new PetBuilderJackson();
//        JsonNode pet = builder.withRequiredFields(16, 22, "cat", "sold").build();
//        System.out.println(pet);
//        PetController controller = new PetController();
//        Response response = controller.addPet(pet);
//        PetAssertions assertions = new PetAssertions();
//        assertions.assertResponseBody(response, pet);


//        System.out.println(RandomStringUtils.random((6), "1234567890"));
//        System.out.println(String.valueOf(new Random().nextInt(3) ));
    }

}

