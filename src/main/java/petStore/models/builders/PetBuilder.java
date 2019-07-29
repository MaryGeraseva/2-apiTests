package petStore.models.builders;

import petStore.models.petModel.Category;
import petStore.models.petModel.PetModel;
import petStore.models.petModel.TagsItem;

import java.util.List;

public class PetBuilder {

    PetModel pet = new PetModel();

    public PetBuilder withRequiredFields(String id, Category category, String status) {
        pet.setId(id);
        pet.setCategory(category);
        pet.setStatus(status);
        return this;
    }

    public PetBuilder withAllFields(String id, Category category, String status, List<String> photoUrls, List<TagsItem> tags, String name) {
        withRequiredFields(id, category, status);
        pet.setPhotoUrls(photoUrls);
        pet.setTags(tags);
        pet.setName(name);
        return this;
    }

    public PetModel build() {
        return pet;
    }
}
