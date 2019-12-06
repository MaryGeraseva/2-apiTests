package petStore.models.petModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class PetModel {

    @JsonProperty("photoUrls")
    private List<String> photoUrls;

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("category")
    private Category category;

    @JsonProperty("tags")
    private List<TagsItem> tags;

    @JsonProperty("status")
    private String status;

    public PetModel() {
    }

    public PetModel(String id, Category category, String status) {
        this.id = id;
        this.category = category;
        this.status = status;
    }

    public PetModel(List<String> photoUrls, String name, String id, Category category, List<TagsItem> tags, String status) {
        this.photoUrls = photoUrls;
        this.name = name;
        this.id = id;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setTags(List<TagsItem> tags) {
        this.tags = tags;
    }

    public List<TagsItem> getTags() {
        return tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format(
                "{\"id\":\"%s\"," +
                        "\"category\":%s," +
                        "\"name\":\"%s\"," +
                        "\"photoUrls\":%s," +
                        "\"tags\":%s," +
                        "\"status\":\"%s\"}",
                getId(), getCategory().toString(), getName(),
                getPhotoUrls().toString(), getTags().toString(), getStatus()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetModel)) return false;
        PetModel petModel = (PetModel) o;
        return Objects.equals(getPhotoUrls(), petModel.getPhotoUrls()) &&
                Objects.equals(getName(), petModel.getName()) &&
                Objects.equals(getId(), petModel.getId()) &&
                Objects.equals(getCategory(), petModel.getCategory()) &&
                Objects.equals(getTags(), petModel.getTags()) &&
                Objects.equals(getStatus(), petModel.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhotoUrls(), getName(), getId(), getCategory(), getTags(), getStatus());
    }
}