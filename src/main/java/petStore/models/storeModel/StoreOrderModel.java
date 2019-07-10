package petStore.models.storeModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.Objects;

@Generated("com.robohorse.robopojogenerator")
public class StoreOrderModel {

    @JsonProperty("petId")
    private Long petId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("shipDate")
    private String shipDate;

    @JsonProperty("complete")
    private boolean complete;

    @JsonProperty("status")
    private String status;

    public StoreOrderModel() {
    }

    public StoreOrderModel(Long petId, Integer quantity, Long id, String shipDate, boolean complete, String status) {
        this.petId = petId;
        this.quantity = quantity;
        this.id = id;
        this.shipDate = shipDate;
        this.complete = complete;
        this.status = status;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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
                "StoreOrderModel{" +
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
        if (!(o instanceof StoreOrderModel)) return false;
        StoreOrderModel order = (StoreOrderModel) o;
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