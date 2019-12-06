package petStore.models.petModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Category {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    public Category() {
    }

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\",\"name\":\"%s\"}", getId(), getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getName(), category.getName()) &&
                (Objects.equals(getId(), category.getId()) ||
                        (Objects.equals(getId(), null) &&
                                Objects.equals(category.getId(), "0")));
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}