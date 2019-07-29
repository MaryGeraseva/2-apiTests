package petStore.models.petModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;


public class TagsItem {

    public TagsItem() {
    }

    public TagsItem(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

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
        return
                "TagsItem{" +
                        "name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagsItem)) return false;
        TagsItem tagsItem = (TagsItem) o;
        return Objects.equals(getName(), tagsItem.getName()) &&
                Objects.equals(getId(), tagsItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}