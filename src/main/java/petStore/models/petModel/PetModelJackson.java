package petStore.models.petModel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import petStore.models.enums.PetFields;

import java.util.List;
import java.util.Map;

public class PetModelJackson {

    private ObjectNode category;
    private ArrayNode photoUrls;
    private ArrayNode tags;

    private JsonNodeFactory factory = new JsonNodeFactory(false);

    public ObjectNode getCategory(String categoryId, String categoryName) {
        createCategory();
        category.put(PetFields.ID.getValue(), categoryId)
                .put(PetFields.NAME.getValue(), categoryName);
        return category;
    }

    public void createCategory() {
        category = factory.objectNode();
    }

    public ArrayNode getPhotoUrls(List<String> urls) {
        createPhotoUrls();
        for(String url : urls) {
            photoUrls.add(url);
        }
        return photoUrls;
    }

    public ArrayNode createPhotoUrls() {
        photoUrls = factory.arrayNode();
        return photoUrls;
    }

    public ArrayNode getTags(Map<String, String> tagItems) {
        createTags();
        for(Map.Entry<String, String> entry : tagItems.entrySet()) {
            ObjectNode tagItem = factory.objectNode();
            tagItem.put(PetFields.ID.getValue(), entry.getKey());
            tagItem.put(PetFields.NAME.getValue(), entry.getValue());
            tags.add(tagItem);
        }
        return tags;
    }

    public ArrayNode getTags(String id, String name) {
        createTags();
        ObjectNode tagItem = factory.objectNode();
        tagItem.put(PetFields.ID.getValue(), id);
        tagItem.put(PetFields.NAME.getValue(), name);
        tags.add(tagItem);
        return tags;
    }

    public ArrayNode createTags() {
        tags = factory.arrayNode();
        return tags;
    }
}
