package petStore.models.petModel;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

public class PetModelJackson {

    private ObjectNode category;
    private ArrayNode photoUrls;
    private ArrayNode tags;

    private JsonNodeFactory factory = new JsonNodeFactory(false);

    public ObjectNode setCategory(String categoryId, String categoryName) {
        getCategory();
        category.put("id", categoryId)
                .put("name", categoryName);
        return category;
    }

    public void getCategory() {
        category = factory.objectNode();
    }

    public ArrayNode setPhotoUrls(List<String> urls) {
        getPhotoUrls();
        for(String url : urls) {
            photoUrls.add(url);
        }
        return photoUrls;
    }

    public ArrayNode getPhotoUrls() {
        photoUrls = factory.arrayNode();
        return photoUrls;
    }

    public ArrayNode setTags(Map<String, String> tagItems) {
        getTags();
        for(Map.Entry<String, String> entry : tagItems.entrySet()) {
            ObjectNode tagItem = factory.objectNode();
            tagItem.put("id", entry.getKey());
            tagItem.put("name", entry.getValue());
            tags.add(tagItem);
        }
        return tags;
    }

    public ArrayNode getTags() {
        tags = factory.arrayNode();
        return tags;
    }
}
