package petStore.models.builders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import petStore.models.petModel.Category;

public class FakePetBuilder {

    ObjectNode pet;

    public FakePetBuilder withRequiredFields(int id, int categoryId, String categoryName, String status) {
        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectMapper mapper = new ObjectMapper();

        pet = factory.objectNode();
        ObjectNode category = factory.objectNode();
        ArrayNode photoUrls = factory.arrayNode();
        ObjectNode tagItem = factory.objectNode();
        ArrayNode tags = factory.arrayNode();

        category.put("id", categoryId)
                .put("name", categoryName);
//        tags.add(tagItem);

        pet.put("id", id);
        pet.set("category", category);
        pet.put("name", "");
        pet.set("photoUrls", photoUrls);
        pet.set("tags", tags);
        pet.put("status", status);
        return this;
    }

//    public FakePetBuilder withRequiredFields(String id, String category, String status) {
//        fakePet = mapper.createObjectNode()
//                .put("id", id)
//                .put("category", category)
//                .put("status", status);
//        return this;
//    }
//
//    public FakePetBuilder withAllFields(String id, String category, String status, String photoUrls, String tags, String name) {
//        fakePet = mapper.createObjectNode()
//                .put("id", id)
//                .put("category", category)
//                .put("status", status)
//                .put("photoUrls", photoUrls)
//                .put("tags", tags)
//                .put("name", name);
//        return this;
//    }

    public JsonNode build() {
        return pet;
    }
}
