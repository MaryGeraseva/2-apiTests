package petStore.models.builders;

import petStore.models.storeModel.OrderModel;

public class OrderBuilder {

    OrderModel order = new OrderModel();

    public OrderBuilder withRequiredFields(Long id, Long petId, Integer quantity, String status, Boolean complete) {
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(quantity);
        order.setStatus(status);
        order.setComplete(complete);
        return this;
    }

    public OrderBuilder withAllFields(Long id, Long petId, Integer quantity, String status, Boolean complete, String shipDate) {
        withRequiredFields(id, petId, quantity, status, complete);
        order.setShipDate(shipDate);
        return this;
    }

    public OrderModel build() {
        return order;
    }
}
