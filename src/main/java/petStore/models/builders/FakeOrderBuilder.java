package petStore.models.builders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FakeOrderBuilder {

    JsonNode fakeOrder;
    ObjectMapper mapper = new ObjectMapper();

    public FakeOrderBuilder withRequiredFields(String id, String petId, String quantity, String status, String complete) {
        fakeOrder = mapper.createObjectNode()
                .put("id", id)
                .put("petId", petId)
                .put("quantity", quantity)
                .put("status", status)
                .put("complete", complete);
        return this;
    }

    public FakeOrderBuilder withAllFields(String id, String petId, String quantity, String status, String complete, String shipDate) {
        fakeOrder = mapper.createObjectNode()
                .put("id", id)
                .put("petId", petId)
                .put("quantity", quantity)
                .put("status", status)
                .put("complete", complete)
                .put("shipDate", shipDate);
        return this;
    }

    public JsonNode build() {
        return fakeOrder;
    }
}
