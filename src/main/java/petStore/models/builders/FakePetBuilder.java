package petStore.models.builders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FakePetBuilder {

    JsonNode fakePet;
    ObjectMapper mapper = new ObjectMapper();

    public FakePetBuilder withRequiredFields(String id, String category, String status) {
        fakePet = mapper.createObjectNode()
                .put("id", id)
                .put("category", category)
                .put("status", status);
        return this;
    }

    public FakePetBuilder withAllFields(String id, String category, String status, String photoUrls, String tags, String name) {
        fakePet = mapper.createObjectNode()
                .put("id", id)
                .put("category", category)
                .put("status", status)
                .put("photoUrls", photoUrls)
                .put("tags", tags)
                .put("name", name);
        return this;
    }

    public JsonNode build() {
        return fakePet;
    }
}
