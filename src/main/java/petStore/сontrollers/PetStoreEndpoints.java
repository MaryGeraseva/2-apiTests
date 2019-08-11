package petStore.сontrollers;

public enum PetStoreEndpoints {

    URL("http://petstore.swagger.io/v2"),
    PET("/pet"),
    PETS_BY_STATUS("/pet/findByStatus"),
    STORE("/store/order"),
    STORE_ALL_INVENTORY("/store/order");

    private String path;

    private PetStoreEndpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
