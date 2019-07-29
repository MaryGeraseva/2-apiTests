package petStore.models.builders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import petStore.models.petModel.PetModelJackson;

import java.util.List;
import java.util.Map;

public class PetBuilderJackson {

    private ObjectNode pet;
    private JsonNodeFactory factory;
    private PetModelJackson petModel;

    public PetBuilderJackson withRequiredFields(String id, String categoryId, String categoryName, String status) {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put("id", id);
        pet.set("category", petModel.setCategory(categoryId, categoryName));
        pet.put("name", "");
        pet.set("photoUrls", petModel.getPhotoUrls());
        pet.set("tags", petModel.getTags());
        pet.put("status", status);

        return this;
    }

    public PetBuilderJackson withAllFields(String id, String categoryId, String categoryName, String name,
                                           List<String> photoUrls, Map<String, String> items, String status) {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put("id", id);
        pet.set("category", petModel.setCategory(categoryId, categoryName));
        pet.put("name", name);
        pet.set("photoUrls", petModel.setPhotoUrls(photoUrls));
        pet.set("tags", petModel.setTags(items));
        pet.put("status", status);
        return this;
    }

    public PetBuilderJackson withAllFields(String id, ObjectNode category, String name,
                                           ArrayNode photoUrls, ArrayNode items, String status) {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put("id", id);
        pet.set("category", category);
        pet.put("name", name);
        pet.set("photoUrls", photoUrls);
        pet.set("tags", items);
        pet.put("status", status);
        return this;
    }

    public JsonNode build() {
        return pet;
    }
}
