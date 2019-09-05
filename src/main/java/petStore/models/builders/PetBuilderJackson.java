package petStore.models.builders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.RandomStringUtils;
import petStore.models.enums.PetFields;
import petStore.models.enums.PetStatuses;
import petStore.models.petModel.PetModelJackson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static petStore.models.enums.PetFields.*;

public class PetBuilderJackson {

    private ObjectNode pet;
    private JsonNodeFactory factory;
    private PetModelJackson petModel;

    public PetBuilderJackson withRequiredFields(String id, String categoryId, String categoryName, String status) {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put(ID.getValue(), id);
        pet.set(CATEGORY.getValue(), petModel.getCategory(categoryId, categoryName));
        pet.put(NAME.getValue(), EMPTY.getValue());
        pet.set(PHOTO_URLS.getValue(), petModel.createPhotoUrls());
        pet.set(TAGS.getValue(), petModel.createTags());
        pet.put(STATUS.getValue(), status);

        return this;
    }

    public PetBuilderJackson withAllFields(String id, String categoryId, String categoryName, String name,
                                           List<String> photoUrls, Map<String, String> items, String status) {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put(ID.getValue(), id);
        pet.set(CATEGORY.getValue(), petModel.getCategory(categoryId, categoryName));
        pet.put(NAME.getValue(), name);
        pet.set(PHOTO_URLS.getValue(), petModel.getPhotoUrls(photoUrls));
        pet.set(TAGS.getValue(), petModel.getTags(items));
        pet.put(STATUS.getValue(), status);

        return this;
    }

    public PetBuilderJackson withAllFields(String id, ObjectNode category, String name,
                                           ArrayNode photoUrls, ArrayNode items, String status) {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put(ID.getValue(), id);
        pet.set(CATEGORY.getValue(), category);
        pet.put(NAME.getValue(), name);
        pet.set(PHOTO_URLS.getValue(), photoUrls);
        pet.set(TAGS.getValue(), items);
        pet.put(STATUS.getValue(), status);

        return this;
    }

    public PetBuilderJackson withAllFields() {
        petModel = new PetModelJackson();
        factory = new JsonNodeFactory(false);
        pet = factory.objectNode();

        pet.put(ID.getValue(), DataGenerator.getNewFieldValue(ID.getValue()));
        pet.set(CATEGORY.getValue(), petModel.getCategory(DataGenerator.getNewFieldValue(ID.getValue()), DataGenerator.getNewFieldValue(NAME.getValue())));
        pet.put(NAME.getValue(), DataGenerator.getNewFieldValue(NAME.getValue()));
        pet.set(PHOTO_URLS.getValue(), petModel.getPhotoUrls(Arrays.asList(DataGenerator.getNewFieldValue(PHOTO_URLS.getValue()))));
        pet.set(TAGS.getValue(), petModel.getTags(DataGenerator.getNewFieldValue(ID.getValue()), DataGenerator.getNewFieldValue(NAME.getValue())));
        pet.put(STATUS.getValue(), PetStatuses.getRandom().nameLowerCase());

        return this;
    }

    private PetBuilderJackson updatePetField(String field, ObjectNode pet) {
        if (field.equals(STATUS.getValue())) {
            String currentStatus = pet.get(STATUS.getValue()).asText();
            pet.put(field, PetStatuses.getNewStatus(currentStatus).nameLowerCase());
        } else if (field.equals(PHOTO_URLS.getValue())) {
            pet.set(PHOTO_URLS.getValue(), petModel.getPhotoUrls(
                    Arrays.asList(RandomStringUtils.randomAlphabetic(5))));
        } else {
            pet.put(field, DataGenerator.getNewFieldValue(field));
        }

        return this;
    }

    private PetBuilderJackson updatePetField(String field, String nestedField, ObjectNode pet) {
        String newNestedField = DataGenerator.getNewFieldValue(nestedField);

        if (field.equals(TAGS.getValue())) {
            JsonNode newTags = pet.get(field);
            JsonNode newTagItem = ((ArrayNode)newTags).get(0);
            ((ObjectNode)newTagItem).put(nestedField, newNestedField);
            pet.set(field, factory.arrayNode().add(newTagItem));
        } else {
            JsonNode mainField = pet.get(field);
            ((ObjectNode)mainField).put(nestedField, newNestedField);
            pet.set(field, mainField);
        }

        return this;
    }

    public PetBuilderJackson getUpdatedPet(ObjectNode pet, String field, String nestedField) {
        if (Objects.equals(nestedField, null) && !Objects.equals(field, null)) {
            updatePetField(field, pet);
        } else if (Objects.equals(nestedField, null)) {
            this.pet = pet;
        } else if (nestedField.equals("all")) {
            withAllFields();
        } else {
            updatePetField(field, nestedField, pet);
        }

        return this;
    }

    public String getPetId() {
        return pet.get(PetFields.ID.getValue()).asText();
    }

    public PetBuilderJackson setPetId(String id) {
        pet.put(ID.getValue(), id);
        return this;
    }

    public ObjectNode build() {
        return pet;
    }
}
