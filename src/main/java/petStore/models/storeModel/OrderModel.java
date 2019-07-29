package petStore.models.storeModel;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Objects;


public class OrderModel {

    @JsonProperty("petId")
    private long petId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("id")
    private long id;

    @JsonProperty("shipDate")
    private String shipDate;

    @JsonProperty("complete")
    private boolean complete;

    @JsonProperty("status")
    private String status;

    public OrderModel() {
    }

    public OrderModel(long petId, int quantity, long id, String shipDate, boolean complete, String status) {
        this.petId = petId;
        this.quantity = quantity;
        this.id = id;
        this.shipDate = shipDate;
        this.complete = complete;
        this.status = status;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public long getPetId() {
        return petId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "OrderModel{" +
                        "petId = '" + petId + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",id = '" + id + '\'' +
                        ",shipDate = '" + shipDate + '\'' +
                        ",complete = '" + complete + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderModel)) return false;
        OrderModel order = (OrderModel) o;
        return isComplete() == order.isComplete() &&
                Objects.equals(getPetId(), order.getPetId()) &&
                Objects.equals(getQuantity(), order.getQuantity()) &&
                Objects.equals(getId(), order.getId()) &&
                Objects.equals(getShipDate(), order.getShipDate()) &&
                Objects.equals(getStatus(), order.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPetId(), getQuantity(), getId(), getShipDate(), isComplete(), getStatus());
    }
}