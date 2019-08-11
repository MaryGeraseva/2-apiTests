package petStore.models.enums;

public enum PetFields {
    ID("id"),
    NAME("name"),
    CATEGORY("category"),
    PHOTO_URLS("photoUrls"),
    TAGS("tags"),
    STATUS("status"),
    EMPTY("");

    private String value;

    PetFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
