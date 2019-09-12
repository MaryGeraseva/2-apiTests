package petStore.сontrollers.enums;

public enum PetStoreEndpoints {

    URL("http://petstore.swagger.io/v2"),
    PET("/pet"),
    PETS_BY_STATUS("/pet/findByStatus");

    private String path;

    private PetStoreEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
